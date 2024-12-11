package com.usermanagment.backend.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class UserException extends RuntimeException {

    private final String message;
    private final HttpStatusCode httpStatus;

    public UserException(String message, HttpStatusCode httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
