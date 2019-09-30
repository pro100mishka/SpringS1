package com.geekbrains.spring.market.errors_handler.exceptions;

public class InvalidStateOfObjectException extends RuntimeException {
    public InvalidStateOfObjectException(String message) {
        super(message);
    }
}
