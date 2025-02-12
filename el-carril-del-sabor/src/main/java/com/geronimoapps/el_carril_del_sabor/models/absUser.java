package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class absUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Column(name="phone_number")
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
