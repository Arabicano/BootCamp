package com.bootcamp.steptwo.util.handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bootcamp.steptwo.util.ApiCommonException;
import com.bootcamp.steptwo.util.ApiErrorResponse;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.method.HandlerMethod;

import lombok.Getter;

@ControllerAdvice
public class ExceptionAdvisor {
    
    @ExceptionHandler(ApiCommonException.class)
    public ResponseEntity<ApiErrorResponse> handleException(ApiCommonException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex);
        return new ResponseEntity<>(apiErrorResponse, ex.getHttpStatus());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ RuntimeException.class, Exception.class, SQLException.class, IllegalArgumentException.class })
    public ErrorMessage internalServerError(Exception e, HandlerMethod handlerMethod, HttpServletRequest request) {

        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getMethod() + "\r\n" + HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "error",
                e.getCause().getCause().getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> errors = result.getFieldErrors();
        return processFieldErrors(errors);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorMessage httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String result = ex.getMessage();
        return processErrorMessage(result);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerError.class)
    public ErrorMessage internalServerError(InternalServerError ex) {
        String result = ex.getMessage();
        return processErrorMessage(result);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SizeLimitExceededException.class)
    public ErrorMessage sizeLimitExceededException(SizeLimitExceededException ex) {
        String result = ex.getMessage();
        return processErrorMessage(result);
    }

    private ErrorMessage processErrorMessage(String message) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "parameter_error", message);
    }

    private Error processFieldErrors(List<org.springframework.validation.FieldError> errors) {
        Error error = new Error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "400-1",
                "Validation Error");
        for (org.springframework.validation.FieldError fieldError : errors) {
            error.addErrors(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }

    @Getter
    static class Errors {
        private String field;
        private String message;

        Errors(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }

    static class ErrorMessage {
        private final int status;
        private final String message;
        private final String messageCode;
        private final String messageDetail;

        ErrorMessage(int status, String message, String messageCode, String messageDetail) {
            this.status = status;
            this.message = message;
            this.messageCode = messageCode;
            this.messageDetail = messageDetail;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public String getMessageCode() {
            return messageCode;
        }

        public String getMessageDetail() {
            return messageDetail;
        }

    }

    static class Error {
        private final int status;
        private final String message;
        private final String messageCode;
        private final String messageDetail;
        private List<Errors> errors = new ArrayList<>();

        Error(int status, String message, String messageCode, String messageDetail) {
            this.status = status;
            this.message = message;
            this.messageCode = messageCode;
            this.messageDetail = messageDetail;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public String getMessageCode() {
            return messageCode;
        }

        public String getMessageDetail() {
            return messageDetail;
        }

        public void addErrors(String field, String message) {
            Errors error = new Errors(field, message);
            errors.add(error);
        }

        public List<Errors> getErrors() {
            return errors;
        }

    }

}
