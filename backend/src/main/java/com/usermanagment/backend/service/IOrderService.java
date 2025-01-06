package com.usermanagment.backend.service;


import com.usermanagment.backend.dto.order.CreateOrderDto;
import com.usermanagment.backend.dto.order.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {

    OrderDto createOrder(CreateOrderDto createOrderDto);

    Page<OrderDto> getAllOrders(Pageable pageable);
}
