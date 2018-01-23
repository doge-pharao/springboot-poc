package com.adplearning.restapimock.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse constraintViolationException(ConstraintViolationException ex) {
        return new ErrorResponse(500, ex.getMessage());
    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse noHandlerFoundException(Exception ex) {
        return new ErrorResponse(404, ex.getMessage());
    }

    @ExceptionHandler(value = { ElementNotFound.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse elementNotFoud(Exception ex) {
        return new ErrorResponse(404, ex.getMessage());
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse unknownException(Exception ex) {
        return new ErrorResponse(500, ex.getMessage());
    }
}