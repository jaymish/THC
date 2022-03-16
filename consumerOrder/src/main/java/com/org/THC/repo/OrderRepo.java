package com.org.THC.repo;

import com.org.THC.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Integer> {
    Order getOrderById(String id);
}
