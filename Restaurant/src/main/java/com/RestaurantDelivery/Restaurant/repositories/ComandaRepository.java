package com.restaurantdelivery.restaurant.repositories;

import com.restaurantdelivery.restaurant.entities.Comanda;
import com.restaurantdelivery.restaurant.entities.Livrator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ComandaRepository extends Repository<Comanda,Integer>{

    @Query("SELECT comanda from Comanda comanda")
    @Transactional(readOnly = true)
    List<Comanda> findAll();

    @Query("SELECT comanda from Comanda comanda WHERE comanda.orderId=:id")
    @Transactional(readOnly = true)
    Comanda findById(@Param("id") Integer id);

    void save(Comanda comanda);

}
