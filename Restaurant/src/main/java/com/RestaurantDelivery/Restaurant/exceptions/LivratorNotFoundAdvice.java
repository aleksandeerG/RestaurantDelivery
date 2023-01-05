package com.restaurantdelivery.restaurant.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LivratorNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(LivratorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(LivratorNotFoundException ex) {
        return ex.getMessage();
    }
}
