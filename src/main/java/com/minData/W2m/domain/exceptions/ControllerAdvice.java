package com.minData.W2m.domain.exceptions;

import com.minData.W2m.domain.exceptions.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse notFoundExceptionHandler(NotFoundException ex) {
        return ExceptionResponse.builder().message(ex.getMessage()).code(HttpStatus.NOT_FOUND.toString()).build();
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse badRequestExceptionHandler(BadRequestException ex) {
        return ExceptionResponse.builder().message(ex.getMessage()).code(HttpStatus.BAD_REQUEST.toString()).build();
    }
}
