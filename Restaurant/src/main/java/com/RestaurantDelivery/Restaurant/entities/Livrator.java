package com.restaurantdelivery.restaurant.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="Livrator")
public class Livrator {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    public Livrator() {

    }

    private enum Status {
        AVAILABLE,
        NOT_AVAILABLE
    }
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name ="telephoneNumber")
    private String telephoneNumber;
    @OneToMany
    private List<Comanda> orders = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setOrders(List<Comanda> orders) {
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public List<Comanda> getOrders() {
        return orders;
    }
    public Comanda getComanda(Integer id){
        for(Comanda comanda:getOrders()){
            Integer compId = comanda.getOrderId();
            if(compId.equals(id)){
                return comanda;
            }
        }
        return null;
    }
    public Livrator(int id, String firstName, String lastName, String telephoneNumber, List<Comanda> orders) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.orders = orders;
    }
}
