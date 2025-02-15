package com.geronimoapps.el_carril_del_sabor.dtos;

public record DTOOrderedProductsRequest(
        Short quantity,
        String details,
        DTOProductRequest product
) {
}
