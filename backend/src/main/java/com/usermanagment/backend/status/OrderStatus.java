package com.usermanagment.backend.status;

import com.usermanagment.backend.permission.UserPermission;
import com.usermanagment.backend.utils.UserUtils;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (value == 1 << 3)
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
}
