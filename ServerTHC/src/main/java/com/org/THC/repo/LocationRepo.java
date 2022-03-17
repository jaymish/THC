package com.org.THC.repo;

import com.org.THC.model.Location;

import java.util.List;


public interface LocationRepo {
    List<Location> getAllLocations();
    Location getLocationById(String locationId);
    Location saveLocation(Location location);
    List<Location> getLocationByZip(int zip);
    Location locationDeactivate(String id);
    Location locationActivate(String id);
    Location updateLocation(Location location);
}
