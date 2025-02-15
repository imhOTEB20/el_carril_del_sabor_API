package com.geronimoapps.el_carril_del_sabor.dtos;

import java.sql.Blob;

public record DTODishRequest(
        String name,
        String description,
        Boolean available,
        Float price,
        Blob img
) {
}
