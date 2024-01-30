package com.sq3.portifoliosSq3.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseError {

    private Integer statusCode;

    private String exceptionCode;

    private Object data;

}
