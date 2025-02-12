package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assignment")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id_order")
    private Order order;

    @Column(name = "assignment_date")
    private LocalDateTime assignmentDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "delivery_assigned", referencedColumnName = "id_delivery_driver")
    private DeliveryDriver deliveryAssigned;

    @ManyToOne
    @JoinColumn(name = "assigned_by", referencedColumnName = "id_admin")
    private Administrator assignedBy;

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LocalDateTime getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(LocalDateTime assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public DeliveryDriver getDeliveryAssigned() {
        return deliveryAssigned;
    }

    public void setDeliveryAssigned(DeliveryDriver deliveryAssigned) {
        this.deliveryAssigned = deliveryAssigned;
    }

    public Administrator getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Administrator assignedBy) {
        this.assignedBy = assignedBy;
    }
}
