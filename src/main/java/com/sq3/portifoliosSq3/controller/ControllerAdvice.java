package com.sq3.portifoliosSq3.controller;

import com.sq3.portifoliosSq3.exceptions.RecNotFoundException;
import com.sq3.portifoliosSq3.exceptions.UserCreateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(RecNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RecNotFoundException exception) { return exception.getMessage(); }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAccessDeniedException(AccessDeniedException exception) { return exception.getMessage(); }

    @ExceptionHandler(UserCreateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserCreateException(UserCreateException exception) { return exception.getMessage(); }

}