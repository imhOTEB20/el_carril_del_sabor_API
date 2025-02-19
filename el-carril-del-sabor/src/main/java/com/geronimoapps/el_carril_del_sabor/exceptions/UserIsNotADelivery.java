package com.geronimoapps.el_carril_del_sabor.exceptions;

public class UserIsNotADelivery extends RuntimeException {
    public UserIsNotADelivery(Long idUser) {
        super("User:"+ idUser + " is not a Delivery.");
    }
}
