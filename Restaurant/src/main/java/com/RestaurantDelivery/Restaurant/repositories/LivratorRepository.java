package com.restaurantdelivery.restaurant.repositories;


import com.restaurantdelivery.restaurant.entities.Livrator;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.domain.Pageable;

@org.springframework.stereotype.Repository
public interface LivratorRepository extends Repository<Livrator,Integer> {


    @Query("SELECT livrator FROM Livrator livrator left join fetch livrator.orders WHERE livrator.id=:id")
    @Transactional(readOnly = true)
    Livrator findById(@Param("id") Integer id);


    @Query("SELECT livrator FROM Livrator livrator")
    @Transactional(readOnly=true)
    Page<Livrator> findAll(Pageable pageable);

    void save(Livrator livrator);
}
