package com.geronimoapps.el_carril_del_sabor.services;

import com.geronimoapps.el_carril_del_sabor.models.*;
import com.geronimoapps.el_carril_del_sabor.repositories.AdminRegisterRepository;
import com.geronimoapps.el_carril_del_sabor.repositories.AdministratorRepository;
import com.geronimoapps.el_carril_del_sabor.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AdminRegisterRepository adminRegisterRepository;

    @Autowired
    public AssignmentService (AssignmentRepository assignmentRepository,
                              AdminRegisterRepository adminRegisterRepository) {
        this.assignmentRepository = assignmentRepository;
        this.adminRegisterRepository = adminRegisterRepository;
    }

    @Transactional
    public Assignment createAssignment(Order order, DeliveryDriver delivery, Administrator admin) {
        if (admin.getFoodOutlet().getId().equals(order.getFoodOutlet().getId())) {
            if (order.getStatus() == StatusOrder.READY) {
                var newAssignment = new Assignment();
                newAssignment.setDeliveryAssigned(delivery);
                newAssignment.setAssignedBy(admin);
                newAssignment.setOrder(order);

                var register = new AdminRegister();
                register.setAction(AdminActions.ASSIGN_DELIVERY);
                register.setReferenceCode(order.getId());
                register.setAdministrator(admin);
                this.adminRegisterRepository.save(register);

                return this.assignmentRepository.save(newAssignment);
            } else {
                throw new RuntimeException("order is not ready.");
            }
        } else {
            throw new RuntimeException("The administrator does not have permissions on this order.");
        }
    }
}
