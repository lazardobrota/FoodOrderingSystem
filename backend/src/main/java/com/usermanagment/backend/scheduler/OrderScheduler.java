package com.usermanagment.backend.scheduler;

import com.usermanagment.backend.model.Order;
import com.usermanagment.backend.repository.IOrderRepo;
import com.usermanagment.backend.status.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class OrderScheduler {

    private final IOrderRepo orderRepo;

    @Scheduled(fixedRate = 10_000)
    public void updateOrderStatus() {
        List<Order> orders = findOrdersReadyForStatusUpdate();

        if (orders.isEmpty())
            return;

        for (Order order : orders) {
            order.setStatus(OrderStatus.getNextOrderStatus(order.getStatus()));
        }

        orderRepo.saveAll(orders);
    }

    private List<Order> findOrdersReadyForStatusUpdate() {
        return orderRepo.findOrdersReadyForStatusUpdate(
                OrderStatus.ORDERED.getValue(),
                OrderStatus.PREPARING.getValue(),
                OrderStatus.IN_DELIVERY.getValue(),
                LocalDateTime.now().minusSeconds(10),
                LocalDateTime.now().minusSeconds(15),
                LocalDateTime.now().minusSeconds(20)
        );
    }
}
