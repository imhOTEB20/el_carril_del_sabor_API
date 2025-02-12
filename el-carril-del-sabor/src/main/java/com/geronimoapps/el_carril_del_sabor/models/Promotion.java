package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;

@Entity
@Table(name = "promotions")
@AttributeOverride(name = "id", column = @Column(name = "id_promotion"))
public class Promotion extends absProduct {

    @Enumerated
    private PromotionType type;

    private Float minimumAmount = null;
    private Float discountPercentage = null;

    public PromotionType getType() {
        return type;
    }

    public void setType(PromotionType type) {
        this.type = type;
    }

    public Float getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(Float minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public Float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
