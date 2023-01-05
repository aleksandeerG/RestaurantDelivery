package com.restaurantdelivery.restaurant.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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

    //orders.stream().anyMatch(order->order.getStatus().equals(Comanda.Status.DELIVERING))
    public boolean available() {
        if( orders.stream().filter(order->order.getStatus().equals(Comanda.Status.CANCELED)).equals(orders.stream()) || orders.isEmpty()){
            setStatus(Status.AVAILABLE);
            return true;
        }
        else
        {
            setStatus(Status.NOT_AVAILABLE);
            return false;
        }
    }

    public enum Status {
        AVAILABLE(0),
        NOT_AVAILABLE(1);
        private final Integer type;

        Status(Integer type) {this.type=type;}
    }
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    @Column(name = "firstName")
    @NotBlank(message = "Prenume trebuie introdus")
    private String firstName;
    @Column(name = "lastName")
    @NotBlank(message = "Nume de familie trebuie introdus")
    private String lastName;
    @Column(name ="telephoneNumber")
    @NotBlank(message = "Numar de telefon trebuie introdus")
    private String telephoneNumber;
    @OneToMany(mappedBy = "livrator",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("livrator")
    private List<Comanda> orders = new ArrayList<>();

    public void addOrder(Comanda comanda){

        orders.add(comanda);
        comanda.setLivrator(this);
    }
    public void removeOrder(Comanda comanda){
        orders.remove(comanda);
        comanda.setLivrator(this);
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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



    public Livrator(int id, Status status, String firstName, String lastName, String telephoneNumber, List<Comanda> orders) {
        this.id = id;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.orders = orders;
    }
}
