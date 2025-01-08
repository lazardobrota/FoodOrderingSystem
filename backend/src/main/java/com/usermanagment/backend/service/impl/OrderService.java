package com.usermanagment.backend.service.impl;

import com.usermanagment.backend.dto.order.CreateOrderDto;
import com.usermanagment.backend.dto.order.OrderDto;
import com.usermanagment.backend.mapper.OrderMapper;
import com.usermanagment.backend.model.Order;
import com.usermanagment.backend.model.OrderDish;
import com.usermanagment.backend.repository.IOrderDishRepo;
import com.usermanagment.backend.repository.IOrderRepo;
import com.usermanagment.backend.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final IOrderRepo orderRepo;
    private final IOrderDishRepo orderDishRepo;
    private final OrderMapper orderMapper;

    @Override
    public Page<OrderDto> getAllOrders(Pageable pageable) {
        return orderRepo.findAll(pageable).map(orderMapper::toOrderDto);
    }

    @Override
    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        Order order = orderRepo.save(orderMapper.toOrder(createOrderDto));

        List<OrderDish> orderDishes = createOrderDto.getDishes()
                .entrySet()
                .stream()
                .map(item -> orderMapper.toOrderDish(order, item.getKey(), item.getValue()))
                .toList();
        orderDishes = orderDishRepo.saveAll(orderDishes);

        return orderMapper.toOrderDto(order);
    }

}
