package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.Order;
import com.geronimoapps.el_carril_del_sabor.models.PaymentMethod;
import com.geronimoapps.el_carril_del_sabor.models.StatusOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DTOOrderResponse(
        Long order_cod,
        DTOShortCustomerResponse customer_data,
        String delivery_address,
        StatusOrder status,
        PaymentMethod payment_method,
        LocalDateTime date,
        List<DTOOrderedProducts> products
) {
    public DTOOrderResponse(Order order) {
        this(
                order.getId(),
                new DTOShortCustomerResponse(order.getCustomer()),
                order.getDeliveryAddress(),
                order.getStatus(),
                order.getPaymentMethod(),
                order.getOrderDate(),
                order.getOrderDetails()
                        .stream()
                        .map(DTOOrderedProducts::new)
                        .collect(Collectors.toList())
        );
    }
}
