package com.geronimoapps.el_carril_del_sabor.repositories;

import com.geronimoapps.el_carril_del_sabor.models.Dish;

import com.geronimoapps.el_carril_del_sabor.models.FoodOutlet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByFoodOutlet(FoodOutlet foodOutlet);
}
