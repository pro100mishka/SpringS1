package com.geekbrains.spring.lesson4.errors_handler;

import com.geekbrains.spring.lesson4.errors_handler.error_responses.ErrorResponse;
import com.geekbrains.spring.lesson4.errors_handler.exceptions.InvalidStateOfObjectException;
import com.geekbrains.spring.lesson4.errors_handler.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException e){
        return new ResponseEntity<>(new ErrorResponse()
                .setMsg(e.getMessage())
                .setStatus(HttpStatus.NOT_FOUND)
                .setTimeStamp(LocalDateTime.now())
                , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> invalidStateOfObject(InvalidStateOfObjectException e){
        return new ResponseEntity<>(new ErrorResponse()
                .setMsg(e.getMessage())
                .setStatus(HttpStatus.BAD_REQUEST)
                .setTimeStamp(LocalDateTime.now())
                , HttpStatus.BAD_REQUEST);
    }

}
