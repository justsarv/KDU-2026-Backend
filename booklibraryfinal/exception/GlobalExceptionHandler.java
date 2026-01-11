package com.example.booklibraryfinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFound(BookNotFoundException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameNotFoundExceptionMINE.class)
    public ResponseEntity<ErrorResponse> handleUserNameNotFound(UserNameNotFoundExceptionMINE ex){
        return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
}
