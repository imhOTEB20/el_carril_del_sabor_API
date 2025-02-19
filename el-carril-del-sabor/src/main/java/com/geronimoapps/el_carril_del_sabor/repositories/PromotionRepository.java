package com.geronimoapps.el_carril_del_sabor.repositories;

import com.geronimoapps.el_carril_del_sabor.models.FoodOutlet;
import com.geronimoapps.el_carril_del_sabor.models.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByFoodOutlet(FoodOutlet foodOutlet);
}
