package com.restaurantdelivery.restaurant;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {
    @RequestMapping("/")
    public String helloWorld(){
        return "Hello";
    }
}
