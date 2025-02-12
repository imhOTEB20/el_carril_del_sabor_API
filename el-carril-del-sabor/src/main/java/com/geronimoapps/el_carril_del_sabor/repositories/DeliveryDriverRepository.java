package com.geronimoapps.el_carril_del_sabor.repositories;

import com.geronimoapps.el_carril_del_sabor.models.DeliveryDriver;
import com.geronimoapps.el_carril_del_sabor.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Long> {
    Optional<DeliveryDriver> findByUser(User user);
}
