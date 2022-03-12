package com.org.THC.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.THC.model.Location;
import com.org.THC.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DefaultLocationService implements LocationService {
    //List<Weather> list=new LinkedList<>();

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private List<Location> locationList;
    String url="http://localhost:8081/location/";

    @Autowired
    public DefaultLocationService(RestTemplate restTemplate, ObjectMapper objectMapper, List<Location> locationList){
        this.restTemplate=restTemplate;
        this.objectMapper=objectMapper;
        this.locationList = locationList;
    }

    @Override
    public boolean createLocations(String name, String addressline1, String addressline2, String city, String state,  int zip){
        Location location=new Location();
        location.setName(name);
        location.setAddressline1(addressline1);
        location.setAddressline2(addressline2);
        location.setCity(city);
        location.setState(state);
        location.setZip(zip);
        return restTemplate.postForObject(url+"create", location, boolean.class);

    }

    @Override
    public List<Location> getAllLocations() {
        //List<Locations> locationsList= (List<Locations>) locationRepository.findAll();
        return restTemplate.getForObject(url+"getAll", locationList.getClass());
    }

    @Override
    public Location getLocationsById(String id) {
        return restTemplate.postForObject(url+"getById", id, Location.class);
    }

    @Override
    public List<Location> getLocationsByZip(int zip) {
        return restTemplate.postForObject(url+"getByzip", zip, locationList.getClass());
    }

    @Override
    public Location cancelLocation(String id) {
        return restTemplate.postForObject(url+"cancel", id, Location.class);
    }

    @Override
    public Location activeLocation(String id) {
        return restTemplate.postForObject(url+"active", id, Location.class);
    }

    @Override
    public Location updateLocation(String id, String name, String addressline1, String addressline2, String city, String state, int zip, String status) {
        Location location=new Location();
        location.setId(id);
        location.setName(name);
        location.setAddressline1(addressline1);
        location.setAddressline2(addressline2);
        location.setCity(city);
        location.setState(state);
        location.setStatus(status);
        location.setZip(zip);
        return restTemplate.postForObject(url+"update", location, Location.class);
    }
}
