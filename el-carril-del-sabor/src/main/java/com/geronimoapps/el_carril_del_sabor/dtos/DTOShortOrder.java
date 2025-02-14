package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.Order;
import com.geronimoapps.el_carril_del_sabor.models.PaymentMethod;

import java.time.LocalDateTime;

public record DTOShortOrder(
        Long cod_order,
        String delivery_address,
        PaymentMethod payment_method,
        LocalDateTime date
) {
    public DTOShortOrder(Order order) {
        this(
                order.getId(),
                order.getDeliveryAddress(),
                order.getPaymentMethod(),
                order.getOrderDate()
        );
    }
}
