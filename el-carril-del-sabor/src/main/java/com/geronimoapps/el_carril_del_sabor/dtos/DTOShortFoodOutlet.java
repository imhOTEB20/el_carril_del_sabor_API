package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.FoodOutlet;

public record DTOShortFoodOutlet(
        Long food_outlet_cod,
        String name
) {
    public DTOShortFoodOutlet(FoodOutlet foodOutlet) {
        this(
                foodOutlet.getId(),
                foodOutlet.getName()
        );
    }
}
