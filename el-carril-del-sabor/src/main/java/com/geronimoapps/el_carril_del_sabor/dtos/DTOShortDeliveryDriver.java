package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.DeliveryDriver;

public record DTOShortDeliveryDriver(
        Long delivery_cod,
        String name,
        String phone_number
) {
    public DTOShortDeliveryDriver(DeliveryDriver deliveryDriver) {
        this(
                deliveryDriver.getId(),
                deliveryDriver.getName(),
                deliveryDriver.getPhoneNumber()
        );
    }
}
