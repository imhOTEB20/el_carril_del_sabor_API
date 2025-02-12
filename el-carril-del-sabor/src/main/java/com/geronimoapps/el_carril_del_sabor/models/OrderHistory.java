package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_histories")
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_history")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id_order")
    private Order order;

    @Enumerated
    private StatusOrder statusOrder;

    private LocalDateTime date = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
