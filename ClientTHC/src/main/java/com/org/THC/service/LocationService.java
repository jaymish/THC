package com.org.THC.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.THC.model.Location;
import com.org.THC.model.PageLocation;

import java.util.List;


public interface LocationService {
    boolean createLocations(String name, String addressline1, String addressline2, String city, String state,  int zip);
    List<Location> getAllLocations();
    Location getLocationsById(String id);
    List<Location> getLocationsByZip(int zip);
    Location deactivateLocation(String id);
    Location activateLocation(String id);
    Location updateLocation( String id, String name, String addressline1, String addressline2, String city, String state, int zip, String status);
    PageLocation getAllpage(Integer pageNo, Integer pageSize,String show);
}
