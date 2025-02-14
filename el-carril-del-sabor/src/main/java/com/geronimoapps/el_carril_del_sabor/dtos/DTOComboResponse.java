package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.Combo;

public record DTOComboResponse(
        DTODishResponse dish,
        Short quantity
) {
    public DTOComboResponse(Combo combo) {
        this(
                new DTODishResponse(combo.getDish()),
                combo.getQuantity()
        );
    }
}
