package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id_order")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "id_customer")
    private Customer customer;

    @NotBlank
    private String deliveryAddress;

    @Enumerated
    private StatusOrder status = StatusOrder.PENDING;

    @Enumerated
    private PaymentMethod paymentMethod;

    private LocalDateTime orderDate = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public @NotBlank String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(@NotBlank String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
