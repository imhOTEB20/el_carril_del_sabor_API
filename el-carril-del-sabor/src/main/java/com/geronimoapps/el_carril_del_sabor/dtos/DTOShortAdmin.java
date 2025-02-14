package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.Administrator;

public record DTOShortAdmin(
        Long admin_cod,
        String name
) {
    public DTOShortAdmin(Administrator administrator) {
        this(
                administrator.getId(),
                administrator.getName()
        );
    }
}
