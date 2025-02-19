package com.geronimoapps.el_carril_del_sabor.exceptions;

public class ProductTypeNotAvailable extends RuntimeException {
    public ProductTypeNotAvailable (String message) {
        super(message);
    }
}
