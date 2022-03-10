package com.org.THC.service;


import com.org.THC.model.Location;

import java.util.List;

public interface LocationService {
    boolean createOrder(Location location);
    List<Location> getAllOrders();
    Location getOrdersById(String id);
    List<Location> getOrdersByZip(int zip);
    Location cancelOrder(String id);

}
