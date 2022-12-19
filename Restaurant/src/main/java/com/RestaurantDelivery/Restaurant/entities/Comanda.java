package com.restaurantdelivery.restaurant.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Comanda")
public class Comanda {

    @Id
    @GeneratedValue
    private int orderId;

    @OneToOne
    @JoinColumn(name = "livrator_id", referencedColumnName = "id")
    private Livrator livrator;

    @Enumerated(EnumType.ORDINAL)
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



    public Comanda() {

    }

    private enum Status{
        PREPARING,
        PREPARED,
        DELIVERING,
        DELIVERED,
        CANCELED
    }


}
