package com.geronimoapps.el_carril_del_sabor.dtos;

import jakarta.validation.constraints.NotNull;

public record DTOComboRequest(
        @NotNull Long dish_cod,
        @NotNull Short quantity
) {
}
