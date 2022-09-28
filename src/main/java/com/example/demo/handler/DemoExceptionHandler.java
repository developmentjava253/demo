package com.example.demo.handler;

import com.example.demo.exception.ValidationTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DemoExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {ValidationTypeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(ValidationTypeException validationTypeException) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST.toString(), validationTypeException.getMessage());
    }
}
