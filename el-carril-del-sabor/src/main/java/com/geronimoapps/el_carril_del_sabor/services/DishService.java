package com.geronimoapps.el_carril_del_sabor.services;

import com.geronimoapps.el_carril_del_sabor.dtos.DTODishRequest;
import com.geronimoapps.el_carril_del_sabor.dtos.DTODishUpdateRequest;
import com.geronimoapps.el_carril_del_sabor.dtos.DTODishResponse;
import com.geronimoapps.el_carril_del_sabor.models.Dish;
import com.geronimoapps.el_carril_del_sabor.models.User;
import com.geronimoapps.el_carril_del_sabor.repositories.AdministratorRepository;
import com.geronimoapps.el_carril_del_sabor.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final AdministratorRepository administratorRepository;

    @Autowired
    public DishService (DishRepository dishRepository,
                        AdministratorRepository administratorRepository) {
        this.dishRepository = dishRepository;
        this.administratorRepository = administratorRepository;
    }

    @Transactional
    public DTODishResponse editDish(Long idDish, DTODishUpdateRequest dishData, User user) {
        var admin = this.administratorRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("The user is not an Administrator."));

        var dish = this.dishRepository.findById(idDish)
                .orElseThrow(() -> new RuntimeException("Dish id is not found"));

        if(admin.getFoodOutlet().getId().equals(dish.getFoodOutlet().getId())) {
            if (dishData.name() != null) dish.setName(dishData.name());
            if (dishData.description() != null) dish.setDescription(dishData.description());
            if (dishData.available() != null) dish.setAvailable(dishData.available());
            if (dishData.price() != null) dish.setPrice(dishData.price());
            if (dishData.img() != null) dish.setImg(dishData.img());
        }

        return new DTODishResponse(this.dishRepository.save(dish));
    }

    @Transactional
    public DTODishResponse createDish(DTODishRequest dishData, User user) {
        var admin = this.administratorRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("The user is not an Administrator."));

        var dish = new Dish();
        //No Null variables
        dish.setName(dishData.name());
        dish.setPrice(dishData.price());
        dish.setModifyBy(null);
        dish.setAvailable(true);
        dish.setFoodOutlet(admin.getFoodOutlet());
        //Optional data
        if(dishData.description() != null) dish.setDescription(dishData.description());
        if(dishData.img() != null) dish.setImg(dishData.img());

        return new DTODishResponse(this.dishRepository.save(dish));
    }
}
