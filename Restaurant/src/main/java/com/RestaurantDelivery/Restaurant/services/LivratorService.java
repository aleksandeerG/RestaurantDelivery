package com.restaurantdelivery.restaurant.services;

import com.restaurantdelivery.restaurant.entities.Comanda;
import com.restaurantdelivery.restaurant.entities.Livrator;
import com.restaurantdelivery.restaurant.exceptions.LivratorNotFoundException;
import com.restaurantdelivery.restaurant.exceptions.LivratorUnavailableException;
import com.restaurantdelivery.restaurant.exceptions.SameStatusException;
import com.restaurantdelivery.restaurant.repositories.ComandaRepository;
import com.restaurantdelivery.restaurant.repositories.LivratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivratorService {


    private final LivratorRepository livratori;
    private final ComandaRepository comenzi;

    @Autowired
    public LivratorService(ComandaRepository comandaService, LivratorRepository livrareService) {
        this.comenzi = comandaService;
        this.livratori = livrareService;
    }

    public Livrator findLivrator(Integer livratorId) {
        return livratori.findById(livratorId);
    }

    public List<Livrator> getAllLivratori() {
        List<Livrator> livratorii = new ArrayList<>();
        Streamable.of(livratori.findAll()).forEach(livratorii::add);
        return livratorii;
    }

    public Livrator saveNewLivrator(Livrator livrator) {
        livratori.save(livrator);
        return livrator;
    }

    public Livrator updateLivrator(Integer id, Livrator livratorDetails) {
        Livrator updateLivrator = livratori.findById(id);
        if (updateLivrator == null) {
            throw new LivratorNotFoundException(id);
        }
        if (updateLivrator.getStatus() == livratorDetails.getStatus())
            throw new SameStatusException();


        updateLivrator.setFirstName(livratorDetails.getFirstName());
        updateLivrator.setLastName(livratorDetails.getLastName());
        updateLivrator.setStatus(livratorDetails.getStatus());
        updateLivrator.setOrders(livratorDetails.getOrders());
        livratori.save(updateLivrator);
        return updateLivrator;
    }

    public Comanda saveComanda(Comanda comanda, int livratorId) {
        Livrator livrator = new Livrator();

        livrator = livratori.findById(livratorId);
        if (livrator == null) {
            throw new LivratorNotFoundException(livratorId);
        } else if (!livrator.available()) {
            throw new LivratorUnavailableException();
        }
        livrator.addOrder(comanda);
        comenzi.save(comanda);
        return comanda;
    }
}
