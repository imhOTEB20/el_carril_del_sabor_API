package com.geronimoapps.el_carril_del_sabor.repositories;

import com.geronimoapps.el_carril_del_sabor.models.Dish;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
