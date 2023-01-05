package com.restaurantdelivery.restaurant.exceptions;

public class LivratorUnavailableException extends RuntimeException{
    public LivratorUnavailableException(){
        super("Livratorul nu este disponibil");
    }
}
