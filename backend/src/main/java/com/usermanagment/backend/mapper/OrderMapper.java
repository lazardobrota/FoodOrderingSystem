package com.usermanagment.backend.mapper;

import com.usermanagment.backend.dto.dish.DishDto;
import com.usermanagment.backend.dto.order.CreateOrderDto;
import com.usermanagment.backend.dto.order.OrderDto;
import com.usermanagment.backend.model.Order;
import com.usermanagment.backend.model.OrderDish;
import com.usermanagment.backend.model.User;
import com.usermanagment.backend.status.OrderStatus;
import com.usermanagment.backend.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final UserMapper userMapper;
    private final DishMapper dishMapper;

    public Order toOrder(CreateOrderDto createOrderDto, User user) {
        Order order = new Order();
        order.setCreatedBy(user);
        order.setScheduleDate(createOrderDto.getCreatedDate());
        order.setStatus(OrderStatus.ORDERED.getValue());
        order.setActive(true);
        return order;
    }

    public Order toOrder(OrderDto orderDto, User user) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCreatedBy(user);
        order.setScheduleDate(orderDto.getCreatedDate());
        order.setStatus(OrderStatus.ORDERED.getValue());
        order.setActive(true);
        return order;
    }

    public OrderDto toOrderDto(Order order, List<DishDto> dishes) {
        return new OrderDto(
                order.getId(),
                OrderStatus.getOrderStatus(order.getStatus()).getName(),
                userMapper.userToUserDto(order.getCreatedBy()),
                order.isActive(),
                order.getScheduleDate(),
                dishes
        );
    }

    public OrderDto toOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                OrderStatus.getOrderStatus(order.getStatus()).getName(),
                userMapper.userToUserDto(order.getCreatedBy()),
                order.isActive(),
                order.getScheduleDate(),
                order.getDishes().stream().map(dishMapper::toDishDto).toList()
        );
    }

    public OrderDish toOrderDish(Order order, DishDto dishDto) {
        OrderDish orderDish = new OrderDish();
        orderDish.setOrder(order);
        orderDish.setDish(dishMapper.toDish(dishDto));
        return orderDish;
    }
}
