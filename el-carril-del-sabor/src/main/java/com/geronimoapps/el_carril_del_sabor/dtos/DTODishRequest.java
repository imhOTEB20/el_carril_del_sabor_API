package com.geronimoapps.el_carril_del_sabor.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Blob;

public record DTODishRequest(
        @NotBlank
        String name,
        String description,
        @NotNull
        Float price,
        Blob img
) {
}
