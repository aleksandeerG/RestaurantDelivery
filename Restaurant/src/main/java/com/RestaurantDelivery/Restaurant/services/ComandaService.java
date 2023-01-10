package com.restaurantdelivery.restaurant.services;

import com.restaurantdelivery.restaurant.entities.Comanda;
import com.restaurantdelivery.restaurant.repositories.ComandaRepository;
import com.restaurantdelivery.restaurant.repositories.LivratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComandaService {

    private final ComandaRepository comenzi;
    private final LivratorRepository livratori;

    @Autowired
    public ComandaService(ComandaRepository comandaService, LivratorRepository livrareService) {
        this.comenzi = comandaService;
        this.livratori = livrareService;
    }

    public List<Comanda> getComenzi(){
        List<Comanda> comenzile = new ArrayList<>();
        Streamable.of(comenzi.findAll()).forEach(comenzile::add);
        return comenzile;
    }
}
