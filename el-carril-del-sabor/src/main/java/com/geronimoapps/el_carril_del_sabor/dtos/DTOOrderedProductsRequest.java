package com.geronimoapps.el_carril_del_sabor.dtos;

import jakarta.validation.constraints.NotNull;

public record DTOOrderedProductsRequest(
        @NotNull Short quantity,
        String details,
        @NotNull DTOProductRequest product
) {
}
