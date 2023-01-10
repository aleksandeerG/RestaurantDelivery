package com.restaurantdelivery.restaurant.controllers;

import com.restaurantdelivery.restaurant.entities.Comanda;
import com.restaurantdelivery.restaurant.entities.Livrator;
import com.restaurantdelivery.restaurant.exceptions.LivratorNotFoundException;
import com.restaurantdelivery.restaurant.exceptions.LivratorUnavailableException;
import com.restaurantdelivery.restaurant.repositories.ComandaRepository;
import com.restaurantdelivery.restaurant.repositories.LivratorRepository;
import com.restaurantdelivery.restaurant.services.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ComandaController {

    @Autowired
    private ComandaService comandaService;
    //public ComandaController(ComandaRepository comandaService){this.comenzi=comandaService;}

    @GetMapping("/comenzi/get-all")
    public ResponseEntity<List<Comanda>> getAllComenzi() {

        return ResponseEntity.ok(comandaService.getComenzi());
    }


}
