package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.Promotion;
import com.geronimoapps.el_carril_del_sabor.models.PromotionType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record DTOPromotionResponse(
        Long promotion_cod,
        PromotionType type,
        String name,
        String description,
        Boolean available,
        Optional<Float> price,
        Optional<Float> minimum_amount,
        Optional<Float> discount_percentage,
        Optional<List<DTOComboResponse>> products_included
) implements iDTOProduct{
    public DTOPromotionResponse(Promotion promotion) {
        this(
                promotion.getId(),
                promotion.getType(),
                promotion.getName(),
                promotion.getDescription(),
                promotion.getAvailable(),
                Optional.ofNullable(promotion.getPrice()),
                Optional.ofNullable(promotion.getMinimumAmount()),
                Optional.ofNullable(promotion.getDiscountPercentage()),
                promotion.getType() == PromotionType.COMBO
                        ? Optional.of(promotion.getCombos()
                                .stream()
                                .map(DTOComboResponse::new)
                                .collect(Collectors.toList()))
                        : Optional.empty()
        );
    }
}
