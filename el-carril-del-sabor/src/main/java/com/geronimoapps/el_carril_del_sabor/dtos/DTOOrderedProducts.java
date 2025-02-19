package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.OrderDetail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOOrderedProducts(
        @NotBlank iDTOProduct product,
        String details,
        @NotNull Short quantity
) {
    public DTOOrderedProducts(OrderDetail orderDetail) {
        this(
                orderDetail.getDish() == null
                ? new DTODishResponse(orderDetail.getDish()) : new DTOPromotionResponse(orderDetail.getPromotion()),
                orderDetail.getDetails(),
                orderDetail.getQuantity()
        );
    }
}
