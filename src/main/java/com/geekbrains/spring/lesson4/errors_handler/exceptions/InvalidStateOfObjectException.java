package com.geekbrains.spring.lesson4.errors_handler.exceptions;

public class InvalidStateOfObjectException extends RuntimeException {
    public InvalidStateOfObjectException(String message) {
        super(message);
    }
}
