package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "food_outlets")
public class FoodOutlet {

    @Id
    @Column(name = "id_food_outlet")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private LocalDateTime morningOpeningHours;

    private LocalDateTime morningClosingHours;

    private LocalDateTime eveningOpeningHours;

    private LocalDateTime eveningClosingHours;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getMorningOpeningHours() {
        return morningOpeningHours;
    }

    public void setMorningOpeningHours(LocalDateTime morningOpeningHours) {
        this.morningOpeningHours = morningOpeningHours;
    }

    public LocalDateTime getMorningClosingHours() {
        return morningClosingHours;
    }

    public void setMorningClosingHours(LocalDateTime morningClosingHours) {
        this.morningClosingHours = morningClosingHours;
    }

    public LocalDateTime getEveningOpeningHours() {
        return eveningOpeningHours;
    }

    public void setEveningOpeningHours(LocalDateTime eveningOpeningHours) {
        this.eveningOpeningHours = eveningOpeningHours;
    }

    public LocalDateTime getEveningClosingHours() {
        return eveningClosingHours;
    }

    public void setEveningClosingHours(LocalDateTime eveningClosingHours) {
        this.eveningClosingHours = eveningClosingHours;
    }
}
