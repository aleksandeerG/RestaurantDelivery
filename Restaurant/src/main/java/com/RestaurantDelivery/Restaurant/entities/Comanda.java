package com.restaurantdelivery.restaurant.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name="Comanda")
public class Comanda {

    enum Status {
        PREPARING(0),
        PREPARED(1),
        DELIVERING(2),
        DELIVERED(3),
        CANCELED(4);
        private final Integer type;
        Status(Integer type){this.type=type;}

    }


    @Id
    @GeneratedValue
    private int orderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "livrator_id", referencedColumnName = "id")
    @JsonIgnoreProperties("orders")
    private Livrator livrator;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Livrator getLivrator() {
        return livrator;
    }

    public void setLivrator(Livrator livrator) {
        this.livrator = livrator;
    }

    public Comanda(int orderId, Livrator livrator, Status status) {
        this.orderId = orderId;
        this.livrator = livrator;
        this.status = status;
    }

    public Comanda() {

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }




}
