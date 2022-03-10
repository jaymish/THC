package com.org.THC.repo;

import com.org.THC.model.Location;

import java.util.List;


public interface LocationRepo {
    List<Location> getAllOrders();
    Location getOrderById(String orderId);
    Location saveOrder(Location location);
    List<Location> getOrderByZip(int zip);
    Location orderCancel(String id);
}
