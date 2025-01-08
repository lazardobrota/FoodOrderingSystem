package com.usermanagment.backend.mapper;

import com.usermanagment.backend.dto.dish.DishDto;
import com.usermanagment.backend.dto.order.CreateOrderDto;
import com.usermanagment.backend.dto.order.OrderDto;
import com.usermanagment.backend.model.Dish;
import com.usermanagment.backend.model.Order;
import com.usermanagment.backend.model.OrderDish;
import com.usermanagment.backend.status.OrderStatus;
import com.usermanagment.backend.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final UserMapper userMapper;
    private final DishMapper dishMapper;

    public Order toOrder(CreateOrderDto createOrderDto) {
        Order order = new Order();
        order.setCreatedBy(UserUtils.getUser());
        order.setCreatedDate(createOrderDto.getCreatedDate());
        order.setStatus(OrderStatus.ORDERED.getValue());
        order.setActive(true);
        return order;
    }

    public OrderDto toOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                OrderStatus.getOrderStatus(order.getStatus()).getName(),
                userMapper.userToUserDto(order.getCreatedBy()),
                order.isActive(),
                order.getCreatedDate()
        );
    }

    public OrderDish toOrderDish(Order order, DishDto dishDto, int amount) {
        OrderDish orderDish = new OrderDish();
        orderDish.setOrder(order);
        orderDish.setDish(dishMapper.toDish(dishDto));
        orderDish.setAmount(amount);
        return orderDish;
    }
}
