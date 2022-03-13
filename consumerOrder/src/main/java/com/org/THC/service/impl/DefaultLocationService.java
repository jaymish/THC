package com.org.THC.service.impl;


import com.org.THC.model.Location;
import com.org.THC.model.PageLocation;
import com.org.THC.repo.LocationRepo;
import com.org.THC.repo.PageLocationRepo;
import com.org.THC.service.LocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultLocationService implements LocationService {

    private LocationRepo locationRepo;
    private EmailServiceImpl emailService;
    private PageLocationRepo pageLocationRepo;

    public DefaultLocationService(LocationRepo locationRepo, EmailServiceImpl emailService,PageLocationRepo pageLocationRepo){
        this.locationRepo = locationRepo;
        this.emailService = emailService;
        this.pageLocationRepo=pageLocationRepo;
    }
    @Override
    public boolean createLocation(Location location) {
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
    public Location deactivateLocation(String id) {
        //logic to check if deactivate is possible
        return locationRepo.locationDeactivate(id);
    }

    @Override
    public Location activateLocation(String id) {
        //logic to check if deactivate is possible
        return locationRepo.locationActivate(id);
    }

    @Override
    public Location updateLocation(Location location) {
        return locationRepo.updateLocation(location);
    }

    @Override
    public PageLocation getAllpage(Integer pageNo, Integer pageSize, String sortBy, String show){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Location> pagedResult;
        if(show.equals("all")) {
            pagedResult = pageLocationRepo.findAll(paging);
        }
        else{
            pagedResult = pageLocationRepo.findByStatus(show,paging);
        }
        PageLocation pageLocation = new PageLocation();
        if(pagedResult.hasContent()) {
            pageLocation.setLocationList(pagedResult.getContent());
            pageLocation.setPages(pagedResult.getTotalPages());
            return pageLocation;
        } else {
            pageLocation.setLocationList(null);
            pageLocation.setPages(0);
            return pageLocation;
        }
    }

}
