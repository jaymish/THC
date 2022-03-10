package com.org.THC.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.THC.model.Location;

import java.util.List;

public interface LocationService {
    boolean createLocations(Location location) throws JsonProcessingException;
    List<Location> getAllLocations();
    Location getLocationsById(String id);
    List<Location> getLocationsByZip(int zip);
    Location cancelLocation(String id);
}
