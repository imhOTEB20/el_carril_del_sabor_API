package com.geronimoapps.el_carril_del_sabor.repositories;

import com.geronimoapps.el_carril_del_sabor.models.Customer;
import com.geronimoapps.el_carril_del_sabor.models.Order;
import com.geronimoapps.el_carril_del_sabor.models.StatusOrder;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerOrderByOrderDateDesc(Customer customer);

    List<Order> findByStatus(StatusOrder status);
}
