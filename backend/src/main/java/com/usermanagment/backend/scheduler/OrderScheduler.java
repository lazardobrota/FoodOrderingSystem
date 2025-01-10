package com.usermanagment.backend.scheduler;

import com.usermanagment.backend.model.Order;
import com.usermanagment.backend.repository.IOrderRepo;
import com.usermanagment.backend.status.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class OrderScheduler {

    private final IOrderRepo orderRepo;

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


        for (Order order : orders) {
            order.setStatus(OrderStatus.getNextOrderStatus(order.getStatus()));

            OrderStatus orderStatus = OrderStatus.getOrderStatus(order.getStatus());
            if (order.isActive() && orderStatus == OrderStatus.DELIVERED || orderStatus == OrderStatus.CANCEL)
                order.setActive(false);
        }

        orderRepo.saveAll(orders);
    }
}
