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
    public boolean createLocation(Location location) {
        System.out.println(location);
        locationRepo.saveLocation(location);
        //emailService.sendSimpleMessage(locations.getCustomer().getEmail_id(),"Location Created","Thank you your location was created. Here is the detail of your location \n"+ locations.toString());
        return true;
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> locationList = locationRepo.getAllLocations();
        return locationList;
    }

    @Override
    public Location getLocationsById(String id){
        Location location = locationRepo.getLocationById(id);
        return location;
    }

    @Override
    public List<Location> getLocationsByZip(int zip){
        List<Location> locationList = locationRepo.getLocationByZip(zip);
        return locationList;
    }

    @Override
    public Location cancelLocation(String id) {
        //logic to check if cancel is possible
        return locationRepo.locationCancel(id);
    }

    @Override
    public Location activeLocation(String id) {
        //logic to check if cancel is possible
        return locationRepo.locationActive(id);
    }

    @Override
    public Location updateLocation(Location location) {
        return locationRepo.updateLocation(location);
    }

}
