package com.sq3.portifoliosSq3.exceptions.utils;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidationError {

    private String field;

    private String errorMessage;

}
