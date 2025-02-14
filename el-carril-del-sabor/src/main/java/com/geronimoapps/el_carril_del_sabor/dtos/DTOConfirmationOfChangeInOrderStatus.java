package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.StatusOrder;

public record DTOConfirmationOfChangeInOrderStatus(
        Long order_cod,
        StatusOrder new_status
) {

}
