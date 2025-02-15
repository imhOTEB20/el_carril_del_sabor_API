package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.ProductType;

public record DTOProductRequest(
        ProductType type,
        Long product_cod
) {
}
