package com.org.THC.repo;

import com.org.THC.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders,Integer> {
    Orders getOrderById(String id);
}
