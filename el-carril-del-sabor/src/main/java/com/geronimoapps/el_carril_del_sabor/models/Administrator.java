package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;

@Entity
@Table(name = "administrators")
@AttributeOverride(name = "id", column = @Column(name = "id_admin"))
public class Administrator extends absUser {

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "food_outlet", referencedColumnName = "id_food_outlet")
    private FoodOutlet foodOutlet;

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
