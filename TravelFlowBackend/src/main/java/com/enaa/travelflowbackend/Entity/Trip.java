package com.enaa.travelflowbackend.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destination;
    private String date;
    private double price;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Reservation>reservation;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Trip(String destination, String date, double price) {
        this.destination = destination;
        this.date = date;
        this.price = price;
    }

    public Trip(Long id, String destination, String date, double price) {
        this.id = id;
        this.destination = destination;
        this.date = date;
        this.price = price;
    }

    public Trip() {
    }
}
