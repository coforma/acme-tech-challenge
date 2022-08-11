package com.acme.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericExceptions(@NonNull Exception exception, @NonNull WebRequest webRequest) {

        HttpStatus errorCode = HttpStatus.INTERNAL_SERVER_ERROR;
        String httpBodyMsg =  "Unexpected Internal Server Error";

        if(exception instanceof ResponseStatusException) {
            ResponseStatusException respEx = (ResponseStatusException) exception;
            errorCode = respEx.getStatus();
            if (errorCode.is5xxServerError()){
                httpBodyMsg = String.format("Server Side %s", errorCode);
                LOGGER.error(httpBodyMsg, respEx);
            }
            else if (errorCode.is4xxClientError()){
                httpBodyMsg = String.format("HTTP Client Error %s", errorCode);
                LOGGER.debug(httpBodyMsg);
            }
            else{
                LOGGER.debug("Debug exception %s", errorCode, respEx);
            }
        } else if (exception instanceof AccessDeniedException) {
            errorCode = HttpStatus.FORBIDDEN;
            httpBodyMsg = "FORBIDDEN";
            AccessDeniedException accessDeniedException = (AccessDeniedException) exception;
            LOGGER.debug("Unauthorized Exception %s", exception.getMessage());
        }
        
        return this.handleExceptionInternal(exception, httpBodyMsg, new HttpHeaders(), errorCode, webRequest);
    }
}