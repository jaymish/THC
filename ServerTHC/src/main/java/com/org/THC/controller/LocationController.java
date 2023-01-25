package com.org.THC.controller;


import com.org.THC.THCApplication;
import com.org.THC.model.Location;
import com.org.THC.model.PageLocation;
import com.org.THC.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/location")
public class LocationController {

    private LocationService locationService;
    //private static final Logger logger = LogManager.getLogger(THCApplication.class);
    public LocationController(LocationService locationService ){
        this.locationService = locationService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create Location received from client service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location Saved on DB"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public boolean createLocations(@RequestBody Location location){
        //logger.info("Controller:User trying to save location"+location.getName()+" "+location.getAddressline1());
        locationService.createLocation(location);
        return true;
    }


    @GetMapping(path = "/get-all")
    @Operation(summary = "Get All locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Locations Fetched for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public List<Location> getLocations(){
        return locationService.getAllLocations();
    }

    @PostMapping("/get-by-id")
    @Operation(summary = "Get locations by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location Fetched by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public Location locationsById(@RequestBody String id){
        return locationService.getLocationsById(id);
    }

    @PostMapping("/getByzip")
    @Operation(summary = "Get locations by ZipCode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location Fetched by Zip for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public List<Location> locationsByZip(@RequestBody int zip){
        return locationService.getLocationsByZip(zip);
    }

    @PostMapping("/deactivate")
    @Operation(summary = "Deactivate locations by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location Deactivated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<Location> deactivateLocation(@RequestBody String id){
        //logger.info("Controller:User trying to deactivate location with id: "+id);
        return ResponseEntity.ok(locationService.deactivateLocation(id));
    }

    @PostMapping("/activate")
    @Operation(summary = "Activate locations by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location Activated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<Location> activateLocation(@RequestBody String id){
        //logger.info("Controller:User trying to deactivate location with id: "+id);
        return ResponseEntity.ok(locationService.activateLocation(id));
    }

    @PostMapping("/update")
    @Operation(summary = "Update Location Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated location details Successfully"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<Location> updateLocation(@RequestBody Location location){
        //logger.info("Controller:User trying to update and save location with id: "+location.getId());
        return ResponseEntity.ok(locationService.updateLocation(location));
    }


    @GetMapping("/page")

    @Operation(summary = "Get All locations page-vise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Locations Fetched for client page-vise"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public PageLocation getAllLocation(@RequestParam(defaultValue = "0") Integer pageNo,@RequestParam(defaultValue = "10") Integer pageSize,@RequestParam(defaultValue = "id") String sortBy,@RequestParam(defaultValue = "all") String show){
        return locationService.getAllpage(pageNo, pageSize, sortBy,show);
    }
}
