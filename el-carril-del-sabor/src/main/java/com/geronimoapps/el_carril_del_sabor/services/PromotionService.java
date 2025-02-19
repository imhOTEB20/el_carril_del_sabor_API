package com.geronimoapps.el_carril_del_sabor.services;

import com.geronimoapps.el_carril_del_sabor.dtos.DTOPromotionRequest;
import com.geronimoapps.el_carril_del_sabor.dtos.DTOPromotionResponse;
import com.geronimoapps.el_carril_del_sabor.dtos.DTOUpdatePromotionRequest;
import com.geronimoapps.el_carril_del_sabor.models.Combo;
import com.geronimoapps.el_carril_del_sabor.models.Dish;
import com.geronimoapps.el_carril_del_sabor.models.Promotion;
import com.geronimoapps.el_carril_del_sabor.models.User;
import com.geronimoapps.el_carril_del_sabor.repositories.AdministratorRepository;
import com.geronimoapps.el_carril_del_sabor.repositories.ComboRepository;
import com.geronimoapps.el_carril_del_sabor.repositories.DishRepository;
import com.geronimoapps.el_carril_del_sabor.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final AdministratorRepository administratorRepository;
    private final DishRepository dishRepository;

    private final ComboService comboService;

    @Autowired
    public PromotionService (PromotionRepository promotionRepository,
                             AdministratorRepository administratorRepository,
                             DishRepository dishRepository,
                             ComboService comboService) {
        this.promotionRepository = promotionRepository;
        this.administratorRepository = administratorRepository;
        this.dishRepository = dishRepository;

        this.comboService = comboService;
    }

    @Transactional
    public DTOPromotionResponse updatePromotion(Long idPromotion, DTOUpdatePromotionRequest promotionData, User user) {
        var promotion = this.promotionRepository.findById(idPromotion)
                .orElseThrow(() -> new RuntimeException("Promotion id is not found."));

        var admin = this.administratorRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("User is not Administrator."));

        //general changes
        if (promotionData.name() != null) promotion.setName(promotionData.name());
        if (promotionData.description() != null) promotion.setDescription(promotionData.description());
        if (promotionData.available() != null) promotion.setAvailable(promotionData.available());
        if (promotionData.img() != null) promotion.setImg(promotionData.img());
        //type changes
        switch (promotion.getType()) {
            case COMBO -> {
                if (promotionData.price() != null) promotion.setPrice(promotionData.price());
                if (promotionData.dishes() != null) {
                    this.comboService.deleteCombos(promotion.getCombos());
                    var newCombos = promotionData.dishes()
                            .stream()
                            .map(dishData -> {
                                var dish = this.dishRepository.findById(dishData.dish_cod())
                                        .orElseThrow(() -> new RuntimeException("Dish id is not found."));
                                if (dish.getFoodOutlet().getId().equals(admin.getFoodOutlet().getId())) {
                                    return this.comboService.createCombo(promotion, dish, dishData.quantity());
                                } else
                                    throw new RuntimeException("The administrator does not have permission on the dish.");
                            })
                            .collect(Collectors.toSet());
                    promotion.setCombos(newCombos);
                }
            }
            case DISCOUNT -> {
                if (promotionData.discountPercentage() != null) promotion.setDiscountPercentage(promotionData.discountPercentage());
                if (promotionData.minimumAmount() != null) promotion.setMinimumAmount(promotionData.minimumAmount());
            }
        }
        return new DTOPromotionResponse(this.promotionRepository.save(promotion));
    }

    @Transactional
    public DTOPromotionResponse createPromotion(DTOPromotionRequest promotionData, User user) {
        var admin = this.administratorRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("User is not an Administrator."));

        var promotion = new Promotion();
        promotion.setImg(promotionData.img());
        //Non-null/blank variables
        promotion.setName(promotionData.name());
        promotion.setDescription(promotionData.description());
        promotion.setType(promotionData.type());

        //type variables
        switch (promotionData.type()) {
            case COMBO -> {
                if (promotionData.price() != null) {
                    promotion.setPrice(promotionData.price());
                } else {
                    throw new RuntimeException("BadRequest");
                }
                if (promotionData.dishes() != null) {
                    var newCombos = promotionData.dishes()
                            .stream()
                            .map(dishData -> {
                                var dish = this.dishRepository.findById(dishData.dish_cod())
                                        .orElseThrow(() -> new RuntimeException("BadRequest"));

                                if (dish.getFoodOutlet().getId().equals(admin.getFoodOutlet().getId())) {
                                    return comboService.createCombo(promotion, dish, dishData.quantity());
                                } else {
                                    throw new RuntimeException("Administrator does not have permission on Dish.");
                                }
                            })
                            .collect(Collectors.toSet());

                    promotion.setCombos(newCombos);
                } else {
                    throw new RuntimeException("BadRequest");
                }
            }
            case DISCOUNT -> {
                if (promotionData.minimumAmount() != null) {
                    promotion.setMinimumAmount(promotionData.minimumAmount());
                }
                else { throw new RuntimeException("BadRequest"); }

                if (promotionData.discountPercentage() != null) {
                    promotion.setDiscountPercentage(promotionData.discountPercentage());
                }
                else { throw new RuntimeException("BadRequest"); }
            }
        }
        return new DTOPromotionResponse(this.promotionRepository.save(promotion));
    }
}
