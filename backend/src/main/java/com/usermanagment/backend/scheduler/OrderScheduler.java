package com.usermanagment.backend.scheduler;

import com.usermanagment.backend.dto.order.UpdateOrderStatusDto;
import com.usermanagment.backend.model.Order;
import com.usermanagment.backend.repository.IOrderRepo;
import com.usermanagment.backend.status.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderScheduler {

    private final IOrderRepo orderRepo;
    private final SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 10_000)
    public void updateOrderStatus() {
        List<Order> orders = orderRepo.findOrdersReadyForStatusUpdate();
        if (orders.isEmpty())
            return;

//        System.out.println("AAAAAA");
//        try(ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()) {
//            scheduledExecutorService.schedule(() -> System.out.println("lol"), 2000, TimeUnit.MILLISECONDS);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }

        List<Order> sendStatusChangeOrders = new ArrayList<>();
        for (Order order : orders) {
            int nextStatus = OrderStatus.getNextOrderStatus(order.getStatus());
            if (nextStatus != order.getStatus())
                sendStatusChangeOrders.add(order);

            order.setStatus(nextStatus);
            OrderStatus orderStatus = OrderStatus.getOrderStatus(order.getStatus());

            if (order.isActive() && orderStatus == OrderStatus.DELIVERED || orderStatus == OrderStatus.CANCEL)
                order.setActive(false);
        }

        orderRepo.saveAll(orders);

        for (Order order : sendStatusChangeOrders) {
            try {
                updateStatus(order.getId(), OrderStatus.getOrderStatus(order.getStatus()), order.isActive());
            }catch (MessagingException e){
                log.error("Error while sending order status of order {}", order.getId(), e);
            }
        }
    }

    public void updateStatus(Long orderId, OrderStatus orderStatus, boolean active) {
        System.out.println(orderId + " " + orderStatus);
        messagingTemplate.convertAndSend("/order/status", new UpdateOrderStatusDto(orderId, orderStatus.getName(), active));
    }
}
