package com.geronimoapps.el_carril_del_sabor.dtos;

import com.geronimoapps.el_carril_del_sabor.models.Assignment;

import java.time.LocalDateTime;

public record DTOAssignmentResponse(
        Long assignment_cod,
        DTOShortOrder order_data,
        DTOShortDeliveryDriver delivery_data,
        DTOShortAdmin assigned_by,
        LocalDateTime date
) {
    public DTOAssignmentResponse(Assignment assignment) {
        this(
                assignment.getId(),
                new DTOShortOrder(assignment.getOrder()),
                new DTOShortDeliveryDriver(assignment.getDeliveryAssigned()),
                new DTOShortAdmin(assignment.getAssignedBy()),
                assignment.getAssignmentDate()
        );
    }
}
