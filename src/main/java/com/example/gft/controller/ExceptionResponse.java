package com.example.gft.controller;

import lombok.*;

import java.util.Date;

@Getter @Setter
public class ExceptionResponse {
    private int statusCode;
    private String errorMessage;
    private Date timestamp;
    private String message;
    private String detail;

    @Builder
    public ExceptionResponse(int statusCode, String errorMessage, Date timestamp, String message, String detail) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }
}
