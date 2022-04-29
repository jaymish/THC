package com.org.THC.service.impl;


import com.org.THC.THCApplication;
import com.org.THC.model.Location;
import com.org.THC.model.PageLocation;
import com.org.THC.repo.LocationRepo;
import com.org.THC.repo.PageLocationRepo;
import com.org.THC.service.LocationService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(THCApplication.class);

    public DefaultLocationService(LocationRepo locationRepo, EmailServiceImpl emailService,PageLocationRepo pageLocationRepo){
        this.locationRepo = locationRepo;
        this.emailService = emailService;
        this.pageLocationRepo=pageLocationRepo;
    }
    @Override
    public boolean createLocation(Location location) {
        logger.info("Service:User trying to save location "+location.getName()+" " +location.getAddressline1());
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
        logger.info("Service:User trying to deactivate location with id: "+id);
        return locationRepo.locationDeactivate(id);
    }

    @Override
    public Location activateLocation(String id) {
        //logic to check if deactivate is possible
        logger.info("Service:User trying to activate location with id: "+id);
        return locationRepo.locationActivate(id);
    }

    @Override
    public Location updateLocation(Location location) {
        logger.info("Service:User trying to update and save location with id: "+location.getId());
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
