package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @Column(name = "id_order_detail")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id_order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_dish", referencedColumnName = "id_dish")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "promotion", referencedColumnName = "id_promotion")
    private Promotion promotion;

    private Short quantity = 1;

    String details;

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
