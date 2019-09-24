package com.geekbrains.spring.lesson4.errors_handler.error_responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ErrorResponse {
    private LocalDateTime timeStamp;
    private String msg;
    private HttpStatus status;
}
