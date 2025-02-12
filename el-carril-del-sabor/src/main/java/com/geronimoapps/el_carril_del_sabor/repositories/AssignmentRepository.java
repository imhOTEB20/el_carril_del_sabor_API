package com.geronimoapps.el_carril_del_sabor.repositories;

import com.geronimoapps.el_carril_del_sabor.models.Assignment;
import com.geronimoapps.el_carril_del_sabor.models.DeliveryDriver;
import com.geronimoapps.el_carril_del_sabor.models.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Assignment findByOrder(Order order);
    List<Assignment> findByDeliveryAssigned(DeliveryDriver deliveryDriver);
}
