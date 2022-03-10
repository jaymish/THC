package com.order.consumerorder.service;


import com.order.consumerorder.model.Orders;

import java.util.List;

public interface OrderService {
    boolean createOrder(Orders orders);
    List<Orders> getAllOrders();
    Orders getOrdersById(String id);
    List<Orders> getOrdersByZip(int zip);
    Orders cancelOrder(String id);

}
