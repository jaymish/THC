package com.org.THC.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.THC.model.Location;
import com.org.THC.service.LocationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/weather")
public class LocationController {

    private LocationService locationService;



    @Autowired
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }


    @PostMapping("/addReading")
    @ApiOperation(value = "Location created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Created")
    })
    public boolean addWeatherReading(@RequestBody Location location) throws JsonProcessingException {
        System.out.println(location);
        locationService.createLocations(location);
        return true;
    }

    @GetMapping("/getLocations")
    @ApiOperation(value = "Get All locations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Locations Fetched")
    })
    public List<Location> getAll(){
        return locationService.getAllLocations();
    }

    @PostMapping("/getLocationById")
    @ApiOperation(value = "Get locations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Fetched by Id")
    })
    public Location getById(@RequestBody String id){
        return locationService.getLocationsById(id);
    }

    @PostMapping("/getLocationByZip")
    @ApiOperation(value = "Get locations by ZipCode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Fetched by Zip")
    })
    public List<Location> getLocationsByZip(@RequestBody int zipcode){
        return locationService.getLocationsByZip(zipcode);
    }

    @PostMapping("/cancelLocationById")
    @ApiOperation(value = "Cancel locations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Canceled by Id")
    })
    public Location cancelLocation(@RequestBody String id){
        return locationService.cancelLocation(id);
    }




}
