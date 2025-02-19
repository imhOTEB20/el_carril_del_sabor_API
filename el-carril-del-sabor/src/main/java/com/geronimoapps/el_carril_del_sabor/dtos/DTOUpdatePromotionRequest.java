package com.geronimoapps.el_carril_del_sabor.dtos;

import java.sql.Blob;
import java.util.List;

public record DTOUpdatePromotionRequest(
        String name,
        String description,
        Boolean available,
        Float price,
        Blob img,
        List<DTOComboRequest> dishes,
        Float minimumAmount,
        Float discountPercentage
) {
}
