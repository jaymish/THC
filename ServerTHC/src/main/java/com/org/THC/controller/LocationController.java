package com.org.THC.controller;


import com.org.THC.model.Location;
import com.org.THC.model.PageLocation;
import com.org.THC.service.LocationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/location")
public class LocationController {

    private LocationService locationService;
    public LocationController(LocationService locationService ){
        this.locationService = locationService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create Location received from client service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Saved on DB")
    })
    public boolean createLocations(@RequestBody Location location){
        locationService.createLocation(location);
        return true;
    }


    @GetMapping(path = "/get-all")
    @ApiOperation(value = "Get All locations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Locations Fetched for client")
    })
    public List<Location> getLocations(){
        return locationService.getAllLocations();
    }

    @PostMapping("/get-by-id")
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

    @PostMapping("/deactivate")
    @ApiOperation(value = "Deactivate locations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Deactivateed by Id for client")
    })
    public ResponseEntity<Location> deactivateLocation(@RequestBody String id){
        return ResponseEntity.ok(locationService.deactivateLocation(id));
    }

    @PostMapping("/activate")
    @ApiOperation(value = "Deactivate locations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Deactivateed by Id for client")
    })
    public ResponseEntity<Location> activateLocation(@RequestBody String id){
        return ResponseEntity.ok(locationService.activateLocation(id));
    }

    @PostMapping("/update")
    @ApiOperation(value = "Deactivate locations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Deactivateed by Id for client")
    })
    public ResponseEntity<Location> updateLocation(@RequestBody Location location){
        return ResponseEntity.ok(locationService.updateLocation(location));
    }


    @GetMapping("/page")
    public PageLocation getAllLocation(@RequestParam(defaultValue = "0") Integer pageNo,@RequestParam(defaultValue = "10") Integer pageSize,@RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "all") String show){
        return locationService.getAllpage(pageNo, pageSize, sortBy,show);
    }
}
