package com.usermanagment.backend.mapper;

import com.usermanagment.backend.dto.order.CreateOrderDto;
import com.usermanagment.backend.dto.order.OrderDto;
import com.usermanagment.backend.model.Order;
import com.usermanagment.backend.status.OrderStatus;
import com.usermanagment.backend.utils.UserUtils;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final UserMapper userMapper;

    public Order CreateOrderDtoToOrder(CreateOrderDto createOrderDto) {
        Order order = new Order();
        order.setCreatedBy(UserUtils.getUser());
        order.setCreatedDate(createOrderDto.getCreatedDate());
        return order;
    }

    public OrderDto OrderToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                OrderStatus.getOrderStatus(order.getStatus()).getName(),
                userMapper.userToUserDto(order.getCreatedBy()),
                order.isActive(),
                order.getCreatedDate()
        );
    }
}
