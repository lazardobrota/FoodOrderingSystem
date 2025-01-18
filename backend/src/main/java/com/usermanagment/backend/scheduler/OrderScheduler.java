package com.usermanagment.backend.scheduler;

import com.usermanagment.backend.dto.order.UpdateOrderStatusDto;
import com.usermanagment.backend.model.ErrorMessage;
import com.usermanagment.backend.model.Order;
import com.usermanagment.backend.repository.IErrorMessageRepo;
import com.usermanagment.backend.repository.IOrderRepo;
import com.usermanagment.backend.service.impl.ErrorMessageService;
import com.usermanagment.backend.status.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Transactional
@Component
@RequiredArgsConstructor
public class OrderScheduler {

    private final IOrderRepo orderRepo;
    private final IErrorMessageRepo errorMessageRepo;
    private final SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 10_000)
    public void updateOrderStatus() {
        List<Order> orders = calculateNewList(orderRepo.findOrdersReadyForStatusUpdate());
        if (orders.isEmpty())
            return;

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

    private List<Order> calculateNewList(List<Order> orders) {
        if (orders.size() - 3 <= 0)
            return orders;

        List<Order> removeOrders = new ArrayList<>();
        List<ErrorMessage> errorMessages = new ArrayList<>();
        for (int i = 3; i < orders.size(); i++) {
            Order order = orders.get(i);
            order.setActive(false);

            removeOrders.add(order);
            errorMessages.add(new ErrorMessage(order.getCreatedBy(), order.getScheduleDate(), order.getStatus(), "To many scheduled calls right now"));
        }

        orderRepo.saveAll(removeOrders);
        errorMessageRepo.saveAll(errorMessages);
        System.out.println(errorMessages);

        return orders.subList(0, 3);
    }

    public void updateStatus(Long orderId, OrderStatus orderStatus, boolean active) {
        System.out.println(orderId + " " + orderStatus);
        messagingTemplate.convertAndSend("/order/status", new UpdateOrderStatusDto(orderId, orderStatus.getName(), active));
    }
}
