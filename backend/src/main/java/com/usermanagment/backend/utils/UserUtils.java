package com.usermanagment.backend.utils;

import com.usermanagment.backend.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

    public static User getUser() {
        return (User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
