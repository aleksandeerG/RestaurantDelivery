package com.restaurantdelivery.restaurant.controllers;

import com.restaurantdelivery.restaurant.entities.Livrator;

import org.springframework.transaction.annotation.Transactional;
import com.restaurantdelivery.restaurant.repositories.LivratorRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WelcomeController {

    private final LivratorRepository livratorRepository ;
    public WelcomeController(LivratorRepository livrareService) {
        this.livratorRepository = livrareService;
    }
//    private void show(List<Livrator> livratori){
//        livratori.forEach(System.out::println);
//    }
    @RequestMapping("/")
    public String helloWorld(){
//        List<Livrator> livratori = new ArrayList<>();
//        livratorRepository.insert();
//        livratori = livratorRepository.findAll();
//        show(livratori);
        return "Hello";



    }
}
