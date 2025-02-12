package com.geronimoapps.el_carril_del_sabor.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "dishes")
@AttributeOverride(name = "id", column = @Column(name = "id_dish"))
public class Dish extends absProduct {

}
