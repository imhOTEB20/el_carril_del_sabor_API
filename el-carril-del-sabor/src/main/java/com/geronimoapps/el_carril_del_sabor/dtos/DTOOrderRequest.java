package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.PaymentMethod;

import java.util.List;

public record DTOOrderRequest(
        String delivery_address,
        PaymentMethod payment_method,
        Long food_outlet_cod,
        List<DTOOrderedProductsRequest> ordered_products
) {
}
