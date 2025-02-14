package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Blob;

@MappedSuperclass
public abstract class absProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description = null;

    private Boolean available = true;

    private Float price;

    @ManyToOne
    @JoinColumn(name = "modify_by", referencedColumnName = "id_admin")
    private Administrator modifyBy = null;

    @ManyToOne
    @JoinColumn(name = "food_outlet", referencedColumnName = "id_food_outlet")
    private FoodOutlet foodOutlet;

    private Blob img;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Administrator getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Administrator modifyBy) {
        this.modifyBy = modifyBy;
    }

    public FoodOutlet getFoodOutlet() {
        return foodOutlet;
    }

    public void setFoodOutlet(FoodOutlet foodOutlet) {
        this.foodOutlet = foodOutlet;
    }

    public Blob getImg() {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }
}
