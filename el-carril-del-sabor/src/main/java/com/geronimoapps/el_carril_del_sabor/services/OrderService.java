package com.geronimoapps.el_carril_del_sabor.services;

import com.geronimoapps.el_carril_del_sabor.dtos.DTOOrderRequest;
import com.geronimoapps.el_carril_del_sabor.dtos.DTOOrderResponse;
import com.geronimoapps.el_carril_del_sabor.models.*;
import com.geronimoapps.el_carril_del_sabor.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final AdminRegisterRepository adminRegisterRepository;
    private final CustomerRepository customerRepository;
    private final FoodOutletRepository foodOutletRepository;

    private final ProductService productService;

    @Autowired
    public OrderService (OrderRepository orderRepository,
                         AdminRegisterRepository adminRegisterRepository,
                         CustomerRepository customerRepository,
                         FoodOutletRepository foodOutletRepository,
                         ProductService productService) {

        this.orderRepository = orderRepository;
        this.adminRegisterRepository = adminRegisterRepository;
        this.customerRepository = customerRepository;
        this.foodOutletRepository = foodOutletRepository;

        this.productService = productService;
    }

    @Transactional
    public void acceptOrder(Order order, Administrator admin) {
        if (admin.getFoodOutlet().getId().equals(order.getFoodOutlet().getId())) {
            if (order.getStatus() ==  StatusOrder.PENDING) {
                order.setStatus(StatusOrder.ACCEPTED);
                var register = new AdminRegister();
                register.setAction(AdminActions.ACCEPT_ORDER);
                register.setReferenceCode(order.getId());
                register.setAdministrator(admin);

                this.orderRepository.save(order);
                this.adminRegisterRepository.save(register);

            } throw new RuntimeException("Only pending orders can be accepted.");
        } else {
            throw new RuntimeException("The administrator does not have permissions on this order.");
        }
    }

    @Transactional
    public void rejectOrder(Order order, Administrator admin) {
        if(admin.getFoodOutlet().getId().equals(order.getFoodOutlet().getId())) {
            if (order.getStatus() ==  StatusOrder.PENDING) {

                order.setStatus(StatusOrder.REJECTED);
                var register = new AdminRegister();
                register.setAction(AdminActions.REJECT_ORDER);
                register.setReferenceCode(order.getId());
                register.setAdministrator(admin);

                this.orderRepository.save(order);
                this.adminRegisterRepository.save(register);

            } throw new RuntimeException("Only pending orders can be accepted.");
        } else {
            throw new RuntimeException("The administrator does not have permissions on this order.");
        }
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

    public List<DTOOrderResponse> getOrdersReady(Administrator admin) {
        return this.orderRepository.findByFoodOutletOrderByOrderDateDesc(admin.getFoodOutlet())
                .stream()
                .filter(order -> order.getStatus() == StatusOrder.READY)
                .map(DTOOrderResponse::new)
                .collect(Collectors.toList());
    }

    public List<DTOOrderResponse> getOrderPending(Administrator admin) {
        return this.orderRepository.findByFoodOutletOrderByOrderDateDesc(admin.getFoodOutlet())
                .stream()
                .filter(order -> order.getStatus() == StatusOrder.PENDING)
                .map(DTOOrderResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public DTOOrderResponse createOrder(User user, Long idFoodOutlet, DTOOrderRequest orderData) {
        var customer = this.customerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("The user is not an customer."));

        var foodOutlet = this.foodOutletRepository.findById(idFoodOutlet)
                .orElseThrow(() -> new RuntimeException("Food Outlet id is not found."));

        var order = new Order();
        //setting order
        order.setDeliveryAddress(orderData.delivery_address() != null
            ? orderData.delivery_address() : customer.getAddress());
        order.setPaymentMethod(orderData.payment_method());
        order.setFoodOutlet(foodOutlet);
        order.setStatus(StatusOrder.PENDING);
        //setting ordered product
        var orderedProducts = orderData.ordered_products()
                .stream()
                .map(orderedProduct -> {
                    var orderDetail = new OrderDetail();

                    orderDetail.setOrder(order);
                    orderDetail.setDetails(orderedProduct.details());
                    orderDetail.setQuantity(orderedProduct.quantity());
                    switch (orderedProduct.product().type()) {
                        case DISH -> {
                            orderDetail.setDish((Dish) this.productService.getProductByRequest(orderedProduct.product()));
                            orderDetail.setPromotion(null);
                        }
                        case PRODUCT -> {
                            orderDetail.setPromotion((Promotion) this.productService.getProductByRequest(orderedProduct.product()));
                            orderDetail.setDish(null);
                        }
                        default -> throw new RuntimeException("Product type is not available.");
                    }
                    return orderDetail;
                }).collect(Collectors.toSet());
        //add order details to order
        order.setOrderDetails(orderedProducts);

        return new DTOOrderResponse(this.orderRepository.save(order));
    }

    public void cancelOrder (User user, Long orderCod) {
        var customer = this.customerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("The user is not an customer."));

        var order = this.orderRepository.findById(orderCod)
                .orElseThrow(() -> new RuntimeException("Order id is not found."));

        if (order.getCustomer().getId().equals(customer.getId())) {
            order.setStatus(StatusOrder.CANCELED);
            this.orderRepository.save(order);
        } else throw new RuntimeException("The customer does not have permission to cancel the order.");
    }


}
