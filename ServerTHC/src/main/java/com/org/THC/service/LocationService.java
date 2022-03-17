package com.org.THC.service;


import com.org.THC.model.Location;
import com.org.THC.model.PageLocation;

import java.util.List;

public interface LocationService {
    PageLocation getAllpage(Integer pageNo, Integer pageSize, String sortBy, String show);
    boolean createLocation(Location location);
    List<Location> getAllLocations();
    Location getLocationsById(String id);
    List<Location> getLocationsByZip(int zip);
    Location deactivateLocation(String id);
    Location activateLocation(String id);
    Location updateLocation(Location location);
}
