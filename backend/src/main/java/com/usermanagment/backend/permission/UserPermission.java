package com.usermanagment.backend.permission;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public enum UserPermission {
    CanReadUsers     (1 << 0, "can_read_users"),
    CanCreateUsers   (1 << 1, "can_create_users"),
    CanUpdateUsers   (1 << 2, "can_update_users"),
    CanDeleteUsers   (1 << 3, "can_delete_users"),
    CanSearchOrder   (1 << 4, "can_search_order"),
    CanPlaceOrder    (1 << 5, "can_place_order"),
    CanCancelOrder   (1 << 6, "can_cancel_order"),
    CanTrackOrder    (1 << 7, "can_track_order"),
    CanScheduleOrder (1 << 8, "can_schedule_order"); //256

    private final int value;
    private final String name;

    UserPermission(int value, String name) {
        this.value = value;
        this.name = name;
    }
    public static int toInt(Map<String, Boolean> permissionsMap) {
        int result = 0;

        for (UserPermission userPermission : UserPermission.values()) {
            if (permissionsMap.containsKey(userPermission.name) && permissionsMap.get(userPermission.name))
                result += userPermission.value;
        }

        return result;
    }

    public static Map<String, Boolean> toMap(int permissions) {
        Map<String, Boolean> result = new HashMap<>();
        for (UserPermission userPermission : UserPermission.values()) {
            if ((userPermission.value & permissions) > 0)
                result.put(userPermission.name, true);
            else
                result.put(userPermission.name, false);
        }

        return result;
    }

    public static List<String> toList(int permissions) {
        List<String> result = new ArrayList<>();
        for (UserPermission userPermission : UserPermission.values()) {
            if ((userPermission.value & permissions) > 0)
                result.add(userPermission.name);
        }

        return result;
    }
}
