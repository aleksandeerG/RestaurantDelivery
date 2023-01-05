package com.restaurantdelivery.restaurant.controllers;

import com.restaurantdelivery.restaurant.entities.Comanda;
import com.restaurantdelivery.restaurant.entities.Livrator;
import com.restaurantdelivery.restaurant.exceptions.LivratorNotFoundException;
import com.restaurantdelivery.restaurant.exceptions.LivratorUnavailableException;
import com.restaurantdelivery.restaurant.repositories.ComandaRepository;
import com.restaurantdelivery.restaurant.repositories.LivratorRepository;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ComandaController {

    private final ComandaRepository comenzi;
    private final LivratorRepository livratori;

    public ComandaController(ComandaRepository comandaService, LivratorRepository livrareService) {
        this.comenzi = comandaService;
        this.livratori = livrareService;
    }
    //public ComandaController(ComandaRepository comandaService){this.comenzi=comandaService;}

    @GetMapping("/comenzi/get-all")
    public List<Comanda> getAllComenzi() {
        List<Comanda> comenzile = new ArrayList<>();
        Streamable.of(comenzi.findAll()).forEach(comenzile::add);
        return comenzile;
    }

    @PostMapping("livratori/{livratorId}/comenzi/new")
    public ResponseEntity<Comanda> save(@RequestBody Comanda comanda, @PathVariable("livratorId") int livratorId) {

        Livrator livrator = new Livrator();

        livrator = livratori.findById(livratorId);
        if (livrator == null) {
            throw new LivratorNotFoundException(livratorId);
        } else if (!livrator.available()) {
            throw new LivratorUnavailableException();
        }
        livrator.addOrder(comanda);
        comenzi.save(comanda);


        return ResponseEntity.ok(comanda);

    }
}
