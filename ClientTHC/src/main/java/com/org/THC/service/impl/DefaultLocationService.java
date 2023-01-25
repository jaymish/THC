package com.org.THC.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.THC.THCApplication;
import com.org.THC.model.Location;
import com.org.THC.model.PageLocation;
import com.org.THC.service.LocationService;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DefaultLocationService implements LocationService {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private List<Location> locationList;
    //private static final Logger logger = LogManager.getLogger(THCApplication.class);
    @Value("${url.to.serverthc}")
    private String serverURL;

    String url="location/";

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
        //logger.info("Service:User trying to save location "+name+" " +addressline1+" using microservices");
        return restTemplate.postForObject(serverURL+url+"create", location, boolean.class);

    }

    @Override
    public List<Location> getAllLocations() {
        //List<Locations> locationsList= (List<Locations>) locationRepository.findAll();
        return restTemplate.getForObject(serverURL+url+"get-all", locationList.getClass());
    }

    @Override
    public Location getLocationsById(String id) {
        return restTemplate.postForObject(serverURL+url+"get-by-id", id, Location.class);
    }

    @Override
    public List<Location> getLocationsByZip(int zip) {
        return restTemplate.postForObject(serverURL+url+"getByzip", zip, locationList.getClass());
    }

    @Override
    public Location deactivateLocation(String id) {
        //logger.info("Service:User trying to deactivate location with id: "+id+" using microservices");
        return restTemplate.postForObject(serverURL+url+"deactivate", id, Location.class);
    }

    @Override
    public Location activateLocation(String id) {
        //logger.info("Service:User trying to activate location with id: "+id+" using microservices");
        return restTemplate.postForObject(serverURL+url+"activate", id, Location.class);
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
        //logger.info("Service:User trying to update and save location with id: "+id+" using microservices");
        return restTemplate.postForObject(serverURL+url+"update", location, Location.class);
    }

    @Override
    public PageLocation getAllpage(Integer pageNo, Integer pageSize,String show) {
        PageLocation pageLocation=restTemplate.getForObject(serverURL+url+"page?pageNo="+pageNo+"&pageSize="+pageSize+"&sortBy=id&show="+show,PageLocation.class);
        return pageLocation;
    }
}
