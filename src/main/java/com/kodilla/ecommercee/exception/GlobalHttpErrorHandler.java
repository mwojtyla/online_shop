package com.kodilla.ecommercee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException exception) {
        return new ResponseEntity<>("Order with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderItemNotFoundException.class)
    public ResponseEntity<Object> handleOrderItemNotFoundException(OrderItemNotFoundException exception) {
        return new ResponseEntity<>("OrderItem with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
