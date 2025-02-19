package com.geronimoapps.el_carril_del_sabor.exceptions;

public class UserIsNotACustomer extends RuntimeException {
    public UserIsNotACustomer (Long idUser) {
        super("User:"+ idUser + " is not a customer");
    }
}
