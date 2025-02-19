package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.Administrator;

public record DTOAdministratorResponse(
        Long admin_cod,
        String name,
        String email,
        String phoneNumber,
        DTOShortFoodOutlet food_outlet
) {
    public DTOAdministratorResponse(Administrator administrator) {
        this(
                administrator.getId(),
                administrator.getName(),
                administrator.getUser().getEmail(),
                administrator.getPhoneNumber(),
                new DTOShortFoodOutlet(administrator.getFoodOutlet())
        );
    }
}
