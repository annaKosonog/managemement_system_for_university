package org.nauka.exception.handler.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AppException extends RuntimeException {
    private final String entity;
    private final Long value;
    private final ErrorType type;

    @Override
    public String getMessage() {
        return switch (type) {
            case NOT_FOUND -> STR."\{entity} with id \{value} not found";
            case ALREADY_EXISTS -> STR."\{entity} with id \{value} already exists";
        };
    }
}
