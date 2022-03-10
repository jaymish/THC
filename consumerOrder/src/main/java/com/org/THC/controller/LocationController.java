package com.org.THC.controller;


import com.org.THC.model.Location;
import com.org.THC.service.LocationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/location")
public class LocationController {

    private LocationService locationService;
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }

    @PostMapping("/createLocation")
    @ApiOperation(value = "Create Location received from client service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Saved on DB")
    })
    public boolean createLocations(@RequestBody Location location){
        locationService.createLocation(location);
        return true;
    }


    @GetMapping(path = "/getLocation")
    @ApiOperation(value = "Get All locations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Locations Fetched for client")
    })
    public List<Location> getLocations(){
        return locationService.getAllLocations();
    }

    @PostMapping("/getById")
    @ApiOperation(value = "Get locations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Fetched by Id for client" )
    })
    public Location locationsById(@RequestBody String id){
        return locationService.getLocationsById(id);
    }

    @PostMapping("/getByzip")
    @ApiOperation(value = "Get locations by ZipCode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Fetched by Zip for client")
    })
    public List<Location> locationsByZip(@RequestBody int zip){
        return locationService.getLocationsByZip(zip);
    }

    @PostMapping("/cancel")
    @ApiOperation(value = "Cancel locations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Canceled by Id for client")
    })
    public ResponseEntity<Location> cancelLocation(@RequestBody String id){
        return ResponseEntity.ok(locationService.cancelLocation(id));
    }

    @PostMapping("/active")
    @ApiOperation(value = "Cancel locations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Canceled by Id for client")
    })
    public ResponseEntity<Location> activeLocation(@RequestBody String id){
        return ResponseEntity.ok(locationService.activeLocation(id));
    }

    @PostMapping("/update")
    @ApiOperation(value = "Cancel locations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Canceled by Id for client")
    })
    public ResponseEntity<Location> updateLocation(@RequestBody Location location){
        return ResponseEntity.ok(locationService.updateLocation(location));
    }
}
