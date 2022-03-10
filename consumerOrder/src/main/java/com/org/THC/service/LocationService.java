package com.org.THC.service;


import com.org.THC.model.Location;

import java.util.List;

public interface LocationService {
    boolean createLocation(Location location);
    List<Location> getAllLocations();
    Location getLocationsById(String id);
    List<Location> getLocationsByZip(int zip);
    Location cancelLocation(String id);
    Location activeLocation(String id);

}
