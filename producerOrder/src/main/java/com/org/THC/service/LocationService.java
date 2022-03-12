package com.org.THC.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.THC.model.Location;

import java.util.List;


public interface LocationService {
    boolean createLocations(String name, String addressline1, String addressline2, String city, String state,  int zip);
    List<Location> getAllLocations();
    Location getLocationsById(String id);
    List<Location> getLocationsByZip(int zip);
    Location cancelLocation(String id);
    Location activeLocation(String id);
    Location updateLocation( String id, String name, String addressline1, String addressline2, String city, String state, int zip, String status);
}
