package com.restaurantdelivery.restaurant.exceptions;


public class LivratorNotFoundException extends RuntimeException {


    public LivratorNotFoundException(Integer id){
        super("Could not find livrator "+id);
    }

}
