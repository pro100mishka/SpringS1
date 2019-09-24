package com.geekbrains.spring.lesson4.errors_handler.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
