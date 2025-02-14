package com.geronimoapps.el_carril_del_sabor.services;

import com.geronimoapps.el_carril_del_sabor.dtos.DTOOrderResponse;
import com.geronimoapps.el_carril_del_sabor.models.*;
import com.geronimoapps.el_carril_del_sabor.repositories.AdminRegisterRepository;
import com.geronimoapps.el_carril_del_sabor.repositories.AdministratorRepository;
import com.geronimoapps.el_carril_del_sabor.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final AdministratorRepository administratorRepository;
    private final AdminRegisterRepository adminRegisterRepository;

    @Autowired
    public OrderService (OrderRepository orderRepository,
                         AdministratorRepository administratorRepository,
                         AdminRegisterRepository adminRegisterRepository) {
        this.orderRepository = orderRepository;
        this.administratorRepository = administratorRepository;
        this.adminRegisterRepository = adminRegisterRepository;
    }

    @Transactional
    public void acceptOrder(Order order, User user) {
        if (order.getStatus() ==  StatusOrder.PENDING) {
            var admin = administratorRepository.findByUser(user)
                    .orElseThrow(() -> new RuntimeException("The user is not an administrator."));
            order.setStatus(StatusOrder.ACCEPTED);
            var register = new AdminRegister();
            register.setAction(AdminActions.ACCEPT_ORDER);
            register.setReferenceCode(order.getId());
            register.setAdministrator(admin);

            this.orderRepository.save(order);
            this.adminRegisterRepository.save(register);

        } throw new RuntimeException("Only pending orders can be accepted.");
    }

    @Transactional
    public void rejectOrder(Order order, User user) {
        if (order.getStatus() ==  StatusOrder.PENDING) {
            var admin = administratorRepository.findByUser(user)
                    .orElseThrow(() -> new RuntimeException("The user is not an administrator."));
            order.setStatus(StatusOrder.REJECTED);
            var register = new AdminRegister();
            register.setAction(AdminActions.REJECT_ORDER);
            register.setReferenceCode(order.getId());
            register.setAdministrator(admin);

            this.orderRepository.save(order);
            this.adminRegisterRepository.save(register);

        } throw new RuntimeException("Only pending orders can be accepted.");
    }

    public DTOOrderResponse getOrderById(Long id) {
        var order = this.orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order id is not found."));

        return new DTOOrderResponse(order);
    }

    public List<DTOOrderResponse> getOrdersByCustomer(Customer customer) {
        return this.orderRepository.findByCustomerOrderByOrderDateDesc(customer)
                .stream()
                .map(DTOOrderResponse::new)
                .collect(Collectors.toList());
    }

    public List<DTOOrderResponse> getOrdersByFoodOutlet(FoodOutlet foodOutlet) {
        return this.orderRepository.findByFoodOutletOrderByOrderDateDesc(foodOutlet)
                .stream()
                .map(DTOOrderResponse::new)
                .collect(Collectors.toList());
    }

    public List<DTOOrderResponse> getOrdersReady(User user) {
        var admin = this.administratorRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("The user is not an administrator."));

        return this.orderRepository.findByFoodOutletOrderByOrderDateDesc(admin.getFoodOutlet())
                .stream()
                .filter(order -> order.getStatus() == StatusOrder.READY)
                .map(DTOOrderResponse::new)
                .collect(Collectors.toList());
    }

    public List<DTOOrderResponse> getOrderPending(User user) {
        var admin = this.administratorRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("The user is not an administrator."));

        return this.orderRepository.findByFoodOutletOrderByOrderDateDesc(admin.getFoodOutlet())
                .stream()
                .filter(order -> order.getStatus() == StatusOrder.PENDING)
                .map(DTOOrderResponse::new)
                .collect(Collectors.toList());
    }
}
