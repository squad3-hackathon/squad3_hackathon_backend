package com.sq3.portifoliosSq3.exceptions.models;

import lombok.Getter;

@Getter
public class UserCreateException extends RuntimeException {

    private final String exceptionCode = "USER_CREATE_EXCEPTION";

    public UserCreateException(String message) {
        super(message);
    }
}
