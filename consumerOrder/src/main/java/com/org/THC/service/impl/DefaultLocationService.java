package com.org.THC.service.impl;


import com.org.THC.model.Location;
import com.org.THC.repo.LocationRepo;
import com.org.THC.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultLocationService implements LocationService {

    private LocationRepo locationRepo;
    private EmailServiceImpl emailService;

    public DefaultLocationService(LocationRepo locationRepo, EmailServiceImpl emailService){
        this.locationRepo = locationRepo;
        this.emailService = emailService;
    }
    @Override
    public boolean createOrder(Location location) {
        System.out.println(location);
        locationRepo.saveOrder(location);
        //emailService.sendSimpleMessage(orders.getCustomer().getEmail_id(),"Order Created","Thank you your order was created. Here is the detail of your order \n"+ orders.toString());
        return true;
    }

    @Override
    public List<Location> getAllOrders() {
        List<Location> locationList = locationRepo.getAllOrders();
        return locationList;
    }

    @Override
    public Location getOrdersById(String id){
        Location location = locationRepo.getOrderById(id);
        return location;
    }

    @Override
    public List<Location> getOrdersByZip(int zip){
        List<Location> locationList = locationRepo.getOrderByZip(zip);
        return locationList;
    }

    @Override
    public Location cancelOrder(String id) {
        //logic to check if cancel is possible
        return locationRepo.orderCancel(id);
    }

}
