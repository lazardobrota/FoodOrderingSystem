package com.usermanagment.backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class FoodException extends RuntimeException {

    private final String message;
    private final HttpStatusCode httpStatus;

    public FoodException(String message, HttpStatusCode httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
