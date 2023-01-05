package com.restaurantdelivery.restaurant.exceptions;

public class BadStatusException extends RuntimeException {

    public BadStatusException(){
        super("Statusul introdus nu exista");
    }
}
