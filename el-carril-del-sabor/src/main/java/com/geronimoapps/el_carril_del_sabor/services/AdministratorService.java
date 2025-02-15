package com.geronimoapps.el_carril_del_sabor.services;

import com.geronimoapps.el_carril_del_sabor.dtos.DTOAdministratorResponse;
import com.geronimoapps.el_carril_del_sabor.dtos.DTOAssignmentResponse;
import com.geronimoapps.el_carril_del_sabor.dtos.DTOConfirmationOfChangeInOrderStatus;
import com.geronimoapps.el_carril_del_sabor.models.*;
import com.geronimoapps.el_carril_del_sabor.repositories.AdministratorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {

    private final AdministratorRepository administratorRepository;

    private final AssignmentService assignmentService;
    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public AdministratorService (AdministratorRepository administratorRepository,
                                 OrderService orderService,
                                 AssignmentService assignmentService,
                                 ProductService productService) {

        this.administratorRepository = administratorRepository;

        this.assignmentService = assignmentService;
        this.orderService = orderService;
        this.productService = productService;
    }

    public Administrator findAdministratorByUser(User user) {
        return this.administratorRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("The user is not an administrator."));
    }

    public DTOAdministratorResponse getAdministrator(User user) {
        return new DTOAdministratorResponse(this.findAdministratorByUser(user));
    }

    public DTOAssignmentResponse assignAnOrder(Order order, DeliveryDriver delivery, User user) {
        return new DTOAssignmentResponse(
                this.assignmentService.createAssignment(order, delivery, this.findAdministratorByUser(user))
        );
    }

    public DTOConfirmationOfChangeInOrderStatus changeOrderStatus(Order order, User user, StatusOrder newStatus) {
        switch (newStatus) {
            case ACCEPTED -> this.orderService.acceptOrder(order, this.findAdministratorByUser(user));
            case REJECTED -> this.orderService.rejectOrder(order, this.findAdministratorByUser(user));
            default -> throw new RuntimeException("The administrator can only accept or reject orders");
        }

        return new DTOConfirmationOfChangeInOrderStatus(order.getId(), newStatus);
    }

    public void toggleAvailableProduct(absProduct product, User user) {
        this.productService.toggleAvailable(product, this.findAdministratorByUser(user));
    }
}
