package com.bootcamp.utils;

import lombok.Data;

@Data
public class HandleException extends RuntimeException {
    
    private String code;
    
    public HandleException(String code, String message) {
        super(message);
        this.setCode(code);
    }

    public HandleException(String code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
    }

}
