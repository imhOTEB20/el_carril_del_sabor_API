package com.geronimoapps.el_carril_del_sabor.exceptions;

public class UserIsNotAnAdministrator extends RuntimeException {
    public UserIsNotAnAdministrator (Long idUser) {
        super("User:"+ idUser + " is not an Administrator");
    }
}
