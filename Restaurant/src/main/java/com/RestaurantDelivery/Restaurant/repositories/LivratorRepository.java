package com.restaurantdelivery.restaurant.repositories;


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
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface LivratorRepository extends Repository<Livrator,Integer> {




    @Query("SELECT livrator FROM Livrator livrator left join fetch livrator.orders WHERE livrator.id=:id")
    @Transactional(readOnly = true)
    Livrator findById(@Param("id") Integer id);


    @Query("SELECT livrator FROM Livrator livrator")
    @Transactional(readOnly=true)
    List<Livrator> findAll();

    void save(Livrator livrator);

//    @Modifying
//    @Query(value = "insert into Livrator values (1,'a','a',0,'1')",nativeQuery = true)
//    @Transactional(readOnly = false)
//    void insert();

}
