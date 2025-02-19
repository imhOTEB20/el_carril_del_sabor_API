package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.PromotionType;
import jakarta.validation.constraints.NotBlank;

import java.sql.Blob;
import java.util.List;

public record DTOPromotionRequest(
        @NotBlank String name,
        @NotBlank String description,
        Blob img,
        @NotBlank PromotionType type,
        Float price,
        List<DTOComboRequest> dishes,
        Float minimumAmount,
        Float discountPercentage
) {
}
