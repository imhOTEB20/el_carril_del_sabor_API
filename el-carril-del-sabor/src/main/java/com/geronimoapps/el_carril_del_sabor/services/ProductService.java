package com.geronimoapps.el_carril_del_sabor.services;

import com.geronimoapps.el_carril_del_sabor.dtos.DTODishResponse;
import com.geronimoapps.el_carril_del_sabor.dtos.DTOProductRequest;
import com.geronimoapps.el_carril_del_sabor.dtos.DTOPromotionResponse;
import com.geronimoapps.el_carril_del_sabor.dtos.iDTOProduct;

import com.geronimoapps.el_carril_del_sabor.models.*;

import com.geronimoapps.el_carril_del_sabor.repositories.AdminRegisterRepository;
import com.geronimoapps.el_carril_del_sabor.repositories.DishRepository;
import com.geronimoapps.el_carril_del_sabor.repositories.PromotionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    private final DishRepository dishRepository;
    private final PromotionRepository promotionRepository;
    private final AdminRegisterRepository adminRegisterRepository;

    @Autowired
    public ProductService(DishRepository dishRepository,
                          PromotionRepository promotionRepository,
                          AdminRegisterRepository adminRegisterRepository) {

        this.dishRepository = dishRepository;
        this.promotionRepository = promotionRepository;
        this.adminRegisterRepository = adminRegisterRepository;
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

    @Transactional
    public void toggleAvailable(absProduct product, Administrator admin) {
        if (admin.getFoodOutlet().getId().equals(product.getFoodOutlet().getId())) {
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
        } else {
            throw new RuntimeException("The administrator does not have permissions for this product.");
        }
    }

    public absProduct getProductByRequest(DTOProductRequest productData) {
        return switch (productData.type()) {
            case DISH -> {
                yield this.dishRepository.findById(productData.product_cod())
                        .orElseThrow(() -> new RuntimeException("Dish id is not found."));
            }
            case PRODUCT -> {
                yield this.promotionRepository.findById(productData.product_cod())
                        .orElseThrow(() -> new RuntimeException("Promotion id is not found."));
            }
            default -> throw new RuntimeException("Product category is not available.");
        };
    }

    public List<iDTOProduct> getAllProductByFoodOutlet(FoodOutlet foodOutlet) {
        var dishes = this.dishRepository.findByFoodOutlet(foodOutlet)
                .stream()
                .map(DTODishResponse::new)
                .toList();

        var promotions = this.promotionRepository.findByFoodOutlet(foodOutlet)
                .stream()
                .map(DTOPromotionResponse::new)
                .toList();

        return Stream.concat(dishes.stream(), promotions.stream())
                .collect(Collectors.toList());
    }
}
