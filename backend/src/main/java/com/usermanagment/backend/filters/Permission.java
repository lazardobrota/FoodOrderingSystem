package com.usermanagment.backend.filters;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum Permission {
    Default       (0, "none"),
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
    public static int toInt(List<String> permissions) {
        int result = 0;

        for (String permissionText: permissions) {
            result += Permission.fromString(permissionText).getValue();
        }

        return result;
    }

    public static List<String> toList(int permissions) {
        List<String> result = new ArrayList<>();
        for (Permission permission: Permission.values()) {
            if ((permission.value & permissions) > 0)
                result.add(permission.name);

        }

        return result;
    }

    private static Permission fromString(String permissionText) {
        for (Permission permission: Permission.values()) {
            if (permission.name.equals(permissionText))
                return permission;
        }

        return Default;
    }
}
