package com.org.THC.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.THC.model.Location;

import java.util.List;

public interface LocationService {
    boolean createOrders(Location location) throws JsonProcessingException;
    List<Location> getAllOrders();
    Location getOrdersById(String id);
    List<Location> getOrdersByZip(int zip);
    Location cancelOrder(String id);
}
