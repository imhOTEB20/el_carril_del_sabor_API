package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.Dish;

public record DTODishResponse(
        Long dish_cod,
        String name,
        String description,
        Boolean available,
        Float price
) implements iDTOProduct {
    public DTODishResponse(Dish dish) {
        this(
                dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getAvailable(),
                dish.getPrice()
        );
    }
}
