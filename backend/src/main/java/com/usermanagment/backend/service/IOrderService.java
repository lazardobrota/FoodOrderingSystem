package com.usermanagment.backend.service;


import com.usermanagment.backend.dto.order.CreateOrderDto;
import com.usermanagment.backend.dto.order.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IOrderService {

    OrderDto createOrder(CreateOrderDto createOrderDto);

    OrderDto findOrderById(Long id);

    Page<OrderDto> getAllOrders(Pageable pageable);
}
