package com.order.consumerorder.repo;

import com.order.consumerorder.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepo{
    List<Orders> getAllOrders();
    Orders getOrderById(String orderId);
    Orders saveOrder(Orders orders);
    List<Orders> getOrderByZip(int zip);
    Orders orderCancel(String id);
}
