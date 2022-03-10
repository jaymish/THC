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

    @Autowired
    public DefaultLocationService(RestTemplate restTemplate, ObjectMapper objectMapper, List<Location> locationList){
        this.restTemplate=restTemplate;
        this.objectMapper=objectMapper;
        this.locationList = locationList;
    }

    @Override
    public boolean createLocations(Location location) throws JsonProcessingException {

        return restTemplate.postForObject("http://localhost:8081/createLocation", location, boolean.class);

    }

    @Override
    public List<Location> getAllLocations() {
        //List<Locations> locationsList= (List<Locations>) locationRepository.findAll();
        return restTemplate.getForObject("http://localhost:8081/getLocation", locationList.getClass());
    }

    @Override
    public Location getLocationsById(String id) {
        return restTemplate.postForObject("http://localhost:8081/getById", id, Location.class);
    }

    @Override
    public List<Location> getLocationsByZip(int zip) {
        return restTemplate.postForObject("http://localhost:8081/getByzip", zip, locationList.getClass());
    }

    @Override
    public Location cancelLocation(String id) {
        return restTemplate.postForObject("http://localhost:8081/cancel", id, Location.class);
    }
}
