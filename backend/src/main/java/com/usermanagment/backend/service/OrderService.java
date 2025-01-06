package com.usermanagment.backend.service;

import com.usermanagment.backend.dto.order.CreateOrderDto;
import com.usermanagment.backend.dto.order.OrderDto;
import com.usermanagment.backend.mapper.OrderMapper;
import com.usermanagment.backend.model.Order;
import com.usermanagment.backend.repository.IOrderDishRepo;
import com.usermanagment.backend.repository.IOrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final IOrderRepo orderRepo;
    private final IOrderDishRepo orderDishRepo;
    private final OrderMapper orderMapper;

    @Override
    public Page<OrderDto> getAllOrders(Pageable pageable) {
        return orderRepo.findAll(pageable).map(orderMapper::OrderToOrderDto);
    }

    @Override
    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        Order order = orderMapper.CreateOrderDtoToOrder(createOrderDto);
        orderRepo.save(order);
        return orderMapper.OrderToOrderDto(order);
    }

}
