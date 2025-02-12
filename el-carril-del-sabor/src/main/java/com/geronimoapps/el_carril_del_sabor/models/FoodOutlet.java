package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "food_outlets")
public class FoodOutlet {

    @Id
    @Column(name = "id_food_outlet")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private LocalDateTime morningOpeningHours;

    @NotNull
    private LocalDateTime morningClosingHours;

    @NotNull
    private LocalDateTime eveningOpeningHours;

    @NotNull
    private LocalDateTime eveningClosingHours;

    public Long getId() {
        return id;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank String address) {
        this.address = address;
    }

    public @NotBlank String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotNull LocalDateTime getMorningOpeningHours() {
        return morningOpeningHours;
    }

    public void setMorningOpeningHours(@NotNull LocalDateTime morningOpeningHours) {
        this.morningOpeningHours = morningOpeningHours;
    }

    public @NotNull LocalDateTime getMorningClosingHours() {
        return morningClosingHours;
    }

    public void setMorningClosingHours(@NotNull LocalDateTime morningClosingHours) {
        this.morningClosingHours = morningClosingHours;
    }

    public @NotNull LocalDateTime getEveningOpeningHours() {
        return eveningOpeningHours;
    }

    public void setEveningOpeningHours(@NotNull LocalDateTime eveningOpeningHours) {
        this.eveningOpeningHours = eveningOpeningHours;
    }

    public @NotNull LocalDateTime getEveningClosingHours() {
        return eveningClosingHours;
    }

    public void setEveningClosingHours(@NotNull LocalDateTime eveningClosingHours) {
        this.eveningClosingHours = eveningClosingHours;
    }
}
