package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
@AttributeOverride(name = "id", column = @Column(name = "id_customer"))
public class Customer extends absUser {

    private String address;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
