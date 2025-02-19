package com.geronimoapps.el_carril_del_sabor.services;

import com.geronimoapps.el_carril_del_sabor.models.Combo;
import com.geronimoapps.el_carril_del_sabor.models.Dish;
import com.geronimoapps.el_carril_del_sabor.models.Promotion;
import com.geronimoapps.el_carril_del_sabor.repositories.ComboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class ComboService {

    private final ComboRepository comboRepository;

    @Autowired
    public ComboService (ComboRepository comboRepository) {
        this.comboRepository = comboRepository;
    }

    @Transactional
    public void deleteCombos(Set<Combo> combos) {
        combos.forEach(this.comboRepository::delete);
    }

    @Transactional
    public Combo createCombo(Promotion promotion, Dish dish, Short quantity) {
        var combo = new Combo();
        combo.setPromotion(promotion);
        combo.setDish(dish);
        combo.setQuantity(quantity);

        return combo;
    }
}
