package com.usermanagment.backend.global;

import org.apache.commons.codec.digest.DigestUtils;

public class Global {

    public static String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
