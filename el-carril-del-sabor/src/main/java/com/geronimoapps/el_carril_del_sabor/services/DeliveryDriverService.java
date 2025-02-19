package com.geronimoapps.el_carril_del_sabor.services;

import com.geronimoapps.el_carril_del_sabor.dtos.DTOAssignmentResponse;
import com.geronimoapps.el_carril_del_sabor.models.StatusOrder;
import com.geronimoapps.el_carril_del_sabor.models.User;
import com.geronimoapps.el_carril_del_sabor.repositories.AssignmentRepository;
import com.geronimoapps.el_carril_del_sabor.repositories.DeliveryDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryDriverService {

    private final DeliveryDriverRepository deliveryDriverRepository;
    private final AssignmentRepository assignmentRepository;

    @Autowired
    public DeliveryDriverService (DeliveryDriverRepository deliveryDriverRepository,
                                  AssignmentRepository assignmentRepository) {

        this.deliveryDriverRepository = deliveryDriverRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public List<DTOAssignmentResponse> getAllAssignments(User user) {
        var delivery = this.deliveryDriverRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("User is not a delivery driver."));

        return this.assignmentRepository.findByDeliveryAssigned(delivery)
                .stream()
                .map(DTOAssignmentResponse::new)
                .collect(Collectors.toList());
    }

    public List<DTOAssignmentResponse> getAllAssignmentsReadyToDeliver(User user) {
        var delivery = this.deliveryDriverRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("User is not a delivery driver."));

        return this.assignmentRepository.findByDeliveryAssigned(delivery)
                .stream()
                .filter(assignment -> assignment.getOrder().getStatus() == StatusOrder.READY)
                .map(DTOAssignmentResponse::new)
                .collect(Collectors.toList());
    }
}
