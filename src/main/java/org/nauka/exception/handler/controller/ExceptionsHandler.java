package org.nauka.exception.handler.controller;

import org.nauka.exception.handler.service.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {


    @ExceptionHandler(AppException.class)
    public ResponseEntity<BodyExceptionResponse> handleAppException(AppException ex) {
        HttpStatus status = switch (ex.getType()) {
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case ALREADY_EXISTS -> HttpStatus.CONFLICT;
        };

        BodyExceptionResponse body = new BodyExceptionResponse(
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(status).body(body);
    }
}
