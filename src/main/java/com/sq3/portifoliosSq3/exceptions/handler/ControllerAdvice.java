package com.sq3.portifoliosSq3.exceptions.handler;

import com.sq3.portifoliosSq3.exceptions.ResponseError;
import com.sq3.portifoliosSq3.exceptions.models.InvalidLoginException;
import com.sq3.portifoliosSq3.exceptions.models.RecNotFoundException;
import com.sq3.portifoliosSq3.exceptions.models.UserCreateException;
import com.sq3.portifoliosSq3.exceptions.utils.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RecNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseError> recNotFoundException(RecNotFoundException exception) {
        ResponseError responseError = ResponseError.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .exceptionCode(exception.getExceptionCode())
                .data(exception.getMessage())
                .build();
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ResponseError> accessDeniedException(AccessDeniedException exception) {
        final String exceptionCode = "ACCESS_DENIED_EXCEPTION";
        ResponseError responseError = ResponseError.builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .exceptionCode(exceptionCode)
                .data("Acesso negado!")
                .build();
        return new ResponseEntity<>(responseError, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidLoginException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ResponseError> invalidLoginException(InvalidLoginException exception) {
        ResponseError responseError = ResponseError.builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .exceptionCode(exception.getExceptionCode())
                .data(exception.getMessage())
                .build();
        return new ResponseEntity<>(responseError, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserCreateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> userCreateException(UserCreateException exception) {
        ResponseError responseError = ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .exceptionCode(exception.getExceptionCode())
                .data(exception.getMessage())
                .build();
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ValidationError> validationErrorList = new ArrayList<>();
        ResponseError responseError = ResponseError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .exceptionCode("METHOD_ARGUMENT_NOT_VALID_EXCEPTION")
                .data(exception.getFieldErrors())
                .build();
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseError> exception(Exception exception) {
        ResponseError responseError = ResponseError.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .exceptionCode("EXCEPTION")
                .data("Falha interna. Contate o adminsitrador do sistema")
                .build();
        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}