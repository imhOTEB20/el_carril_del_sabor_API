package com.geronimoapps.el_carril_del_sabor.repositories;

import com.geronimoapps.el_carril_del_sabor.models.Order;
import com.geronimoapps.el_carril_del_sabor.models.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    List<OrderHistory> findByOrder(Order order);
}
