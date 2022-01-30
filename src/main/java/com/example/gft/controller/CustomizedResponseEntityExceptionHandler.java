package com.example.gft.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                ExceptionResponse.builder()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .errorMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .timestamp(new Date())
                        .message(ex.getMessage())
                        .detail(webRequest.getDescription(false))
                        .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                ExceptionResponse.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .errorMessage(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .timestamp(new Date())
                        .message(ex.getMessage())
                        .detail(webRequest.getDescription(false))
                        .build();
        webRequest.getHeader("status");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
