package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DTOOrderRequest(
        String delivery_address,
        @NotBlank PaymentMethod payment_method,
        @NotNull Long food_outlet_cod,
        @NotNull List<DTOOrderedProductsRequest> ordered_products
) {
}
