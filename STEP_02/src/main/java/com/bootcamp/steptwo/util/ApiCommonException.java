package com.bootcamp.steptwo.util;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApiCommonException extends RuntimeException {

    private HttpStatus httpStatus;
    private String messageCode;
    private String message;

    public ApiCommonException(HttpStatus status, String messageCode, String message) {
        this.httpStatus = status;
        this.messageCode = messageCode;
        this.message = message;
    }
}
