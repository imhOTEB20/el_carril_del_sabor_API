package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.Customer;

public record DTOShortCustomerResponse(
        Long customer_cod,
        String name,
        String phone_number,
        String email
) {
    public DTOShortCustomerResponse(Customer customer) {
        this(
                customer.getId(),
                customer.getName(),
                customer.getPhoneNumber(),
                customer.getUser().getEmail()
        );
    }
}
