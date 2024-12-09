package com.usermanagment.backend.filters;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum Permission {
    CanReadUsers  (1 << 0, "can_read_users"),
    CanCreateUsers(1 << 1, "can_create_users"),
    CanUpdateUsers(1 << 2, "can_update_users"),
    CanDeleteUsers(1 << 3, "can_delete_users");

    private final int value;
    private final String name;

    Permission(int value, String name) {
        this.value = value;
        this.name = name;
    }
    public static int toInt(Map<String, Boolean> permissionsMap) {
        int result = 0;

        for (Permission permission: Permission.values()) {
            if (permissionsMap.containsKey(permission.name) && permissionsMap.get(permission.name))
                result += permission.value;
        }

        return result;
    }

    public static Map<String, Boolean> toMap(int permissions) {
        Map<String, Boolean> result = new HashMap<>();
        for (Permission permission: Permission.values()) {
            if ((permission.value & permissions) > 0)
                result.put(permission.name, true);
            else
                result.put(permission.name, false);
        }

        return result;
    }
}
