package com.usermanagment.backend.status;

import com.usermanagment.backend.permission.UserPermission;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum OrderStatus {
    ORDERED     (1 << 0, "ordered"),
    PREPARING   (1 << 1, "preparing"),
    IN_DELIVERY (1 << 2, "in_delivery"),
    DELIVERED   (1 << 3, "delivered"),
    CANCEL      (1 << 4, "cancel");

    private final int    value;
    private final String name;

    OrderStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static int getNextOrderStatus(int value) {
        if (value == DELIVERED.value || value == CANCEL.value)
            return value;

        return value << 1;
    }

    public static OrderStatus getOrderStatus(int value) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if ((orderStatus.value & value) > 0)
                return orderStatus;
        }

        return CANCEL;
    }

    public static int getInt(String name) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.name.equals(name))
                return orderStatus.value;
        }

        return CANCEL.value;
    }

    public static List<OrderStatus> toList(int orderStatuses) {
        List<OrderStatus> result = new ArrayList<>();
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if ((orderStatus.value & orderStatuses) > 0)
                result.add(orderStatus);
        }
        return result;
    }
}
