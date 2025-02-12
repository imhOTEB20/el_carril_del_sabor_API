package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_drivers")
@AttributeOverride(name = "id", column = @Column(name = "id_delivery_driver"))
public class DeliveryDriver extends absUser{

    private LocalDateTime lastActivity;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "food_outlet", referencedColumnName = "id_food_outlet")
    private FoodOutlet foodOutlet;

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FoodOutlet getFoodOutlet() {
        return foodOutlet;
    }

    public void setFoodOutlet(FoodOutlet foodOutlet) {
        this.foodOutlet = foodOutlet;
    }
}
