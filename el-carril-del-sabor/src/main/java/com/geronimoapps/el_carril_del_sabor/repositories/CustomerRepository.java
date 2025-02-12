package com.geronimoapps.el_carril_del_sabor.repositories;

import com.geronimoapps.el_carril_del_sabor.models.Customer;
import com.geronimoapps.el_carril_del_sabor.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findByUser(User user);
}
