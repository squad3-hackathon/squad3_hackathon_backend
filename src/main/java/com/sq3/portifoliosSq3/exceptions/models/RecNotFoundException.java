package com.sq3.portifoliosSq3.exceptions.models;

import lombok.Getter;

@Getter
public class RecNotFoundException extends RuntimeException {

    private final String exceptionCode = "REC_NOT_FOUND_EXCEPTION";

    public RecNotFoundException(String message) {
        super(message);
    }
}
