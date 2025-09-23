package org.nauka.exception.handler.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class BodyExceptionResponse {
    private String message;
    private LocalDateTime time;

}
