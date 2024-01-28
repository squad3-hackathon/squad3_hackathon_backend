package com.sq3.portifoliosSq3.exceptions.models;

import lombok.Getter;

@Getter
public class InvalidLoginException extends RuntimeException {

    private final String exceptionCode = "INVALID_LOGIN_EXCEPTION";

    public InvalidLoginException(String message) {
        super(message);
    }

}
