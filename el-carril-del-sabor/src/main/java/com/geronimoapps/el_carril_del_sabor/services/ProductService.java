package com.geronimoapps.el_carril_del_sabor.services;

import com.geronimoapps.el_carril_del_sabor.models.*;
import com.geronimoapps.el_carril_del_sabor.repositories.AdminRegisterRepository;
import com.geronimoapps.el_carril_del_sabor.repositories.AdministratorRepository;
import com.geronimoapps.el_carril_del_sabor.repositories.DishRepository;
import com.geronimoapps.el_carril_del_sabor.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final DishRepository dishRepository;
    private final PromotionRepository promotionRepository;
    private final AdminRegisterRepository adminRegisterRepository;

    private final AdministratorRepository administratorRepository;

    @Autowired
    public ProductService(DishRepository dishRepository,
                          PromotionRepository promotionRepository,
                          AdminRegisterRepository adminRegisterRepository,
                          AdministratorRepository administratorRepository) {
        this.dishRepository = dishRepository;
        this.promotionRepository = promotionRepository;
        this.adminRegisterRepository = adminRegisterRepository;
        this.administratorRepository = administratorRepository;

    }
    @Transactional
    private void saveProduct(absProduct product) {
        if (product instanceof Dish) {
            this.dishRepository.save((Dish) product);
        } else if (product instanceof Promotion) {
            this.promotionRepository.save((Promotion) product);
        } else {
            throw new RuntimeException("Product is not a valid entity");
        }
    }

    public void toggleAvailable(absProduct product, User user) {
        var admin = this.administratorRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("The user is not an administrator."));
        var register = new AdminRegister();
        register.setReferenceCode(product.getId());
        register.setAdministrator(admin);

        if (!product.getAvailable()) {
            product.setAvailable(true);
            register.setAction(AdminActions.ACTIVE_PRODUCT);
        } else {
            product.setAvailable(false);
            register.setAction(AdminActions.DEACTIVATE_PRODUCT);
        }
        this.adminRegisterRepository.save(register);

        this.saveProduct(product);
    }
}
