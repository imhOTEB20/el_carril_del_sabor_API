package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;

@Entity
@Table(name = "combos")
public class Combo {
    @Id
    @Column(name = "id_combo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_dish", referencedColumnName = "id_dish")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "id_promotion", referencedColumnName = "id_promotion")
    private Promotion promotion;

    private Short quantity = 1;

    public Long getId() {
        return id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }
}
