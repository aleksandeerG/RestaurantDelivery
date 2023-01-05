package com.restaurantdelivery.restaurant.exceptions;

public class SameStatusException extends RuntimeException{
    public SameStatusException(){
        super("Ai introdus acelasi Status");
    }
}
