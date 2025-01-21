package com.usermanagment.backend.service.impl;

import com.usermanagment.backend.dto.order.CreateOrderDto;
import com.usermanagment.backend.dto.order.OrderDto;
import com.usermanagment.backend.model.ErrorMessage;
import com.usermanagment.backend.model.User;
import com.usermanagment.backend.params.SearchParams;
import com.usermanagment.backend.exception.FoodException;
import com.usermanagment.backend.mapper.OrderMapper;
import com.usermanagment.backend.model.Order;
import com.usermanagment.backend.model.OrderDish;
import com.usermanagment.backend.repository.IErrorMessageRepo;
import com.usermanagment.backend.repository.IOrderDishRepo;
import com.usermanagment.backend.repository.IOrderRepo;
import com.usermanagment.backend.repository.IUserRepo;
import com.usermanagment.backend.service.IErrorMessageService;
import com.usermanagment.backend.service.IOrderService;
import com.usermanagment.backend.service.IUserService;
import com.usermanagment.backend.specification.OrderSpecification;
import com.usermanagment.backend.status.OrderStatus;
import com.usermanagment.backend.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.hibernate.StaleObjectStateException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final IOrderRepo orderRepo;
    private final IUserRepo userRepo;
    private final IOrderDishRepo orderDishRepo;
    private final OrderMapper orderMapper;
    private final IErrorMessageService errorMessageService;

    @Transactional
    @Override
    public Page<OrderDto> getAllOrders(Pageable pageable, SearchParams searchParams) {
        OrderSpecification specification = new OrderSpecification(searchParams);
        return orderRepo.findAll(specification.filter(), pageable)
                .map(orderMapper::toOrderDto);
    }

    @Transactional
    @Override
    public OrderDto createOrder(CreateOrderDto createOrderDto) {

        User user =  userRepo.findByEmail(UserUtils.getEmail()).orElseThrow(() -> new FoodException("User not found with given id", HttpStatus.BAD_REQUEST));
        if (createOrderDto.getCreatedDate().isBefore(LocalDateTime.now())) {
            int currActive = orderRepo.countOrderByStatuses(List.of(OrderStatus.PREPARING.getValue(), OrderStatus.IN_DELIVERY.getValue()));
            if (currActive > 3) {
                errorMessageService.saveError(new ErrorMessage(user, createOrderDto.getCreatedDate(), OrderStatus.ORDERED.getValue(), "To many orders calls right now"));
                throw new FoodException("To many Orders right now", HttpStatus.TOO_MANY_REQUESTS);
            }
        }

        Order order = orderRepo.save(orderMapper.toOrder(createOrderDto, user));
        List<OrderDish> orderDishes = createOrderDto.getDishes().stream()
                .map(dishDto -> orderMapper.toOrderDish(order, dishDto))
                .toList();

        orderDishes = orderDishRepo.saveAll(orderDishes);
        order.setDishes(orderDishes.stream().map(OrderDish::getDish).collect(Collectors.toList()));
        return orderMapper.toOrderDto(order);
    }

    @Transactional
    @Override
    public OrderDto findOrderById(Long id) {
        return orderRepo.findById(id)
                .map(orderMapper::toOrderDto)
                .orElseThrow(() -> new FoodException("Order not found with given id", HttpStatus.BAD_REQUEST));
    }

    @Transactional
    @Override
    public OrderDto deleteOrder(Long id) {
        try {
            Order order = orderRepo.findById(id).orElseThrow(() -> new FoodException("Order not found with given id", HttpStatus.BAD_REQUEST));
            order.setActive(false);
            order.setStatus(OrderStatus.CANCEL.getValue());
            return orderMapper.toOrderDto(orderRepo.save(order));
        } catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e) {
            throw new FoodException("Order not up to date", HttpStatus.BAD_REQUEST);
        }
    }

}
