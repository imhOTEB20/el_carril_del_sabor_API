package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.OrderDetail;

public record DTOOrderedProducts(
        iDTOProduct product,
        String details,
        Short quantity
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
