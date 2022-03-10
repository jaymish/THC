package com.order.producerorder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.producerorder.model.Orders;

import java.util.List;

public interface OrderService {
    boolean createOrders(Orders orders) throws JsonProcessingException;
    List<Orders> getAllOrders();
    Orders getOrdersById(String id);
    List<Orders> getOrdersByZip(int zip);
    Orders cancelOrder(String id);
}
