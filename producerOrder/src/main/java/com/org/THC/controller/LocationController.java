package com.org.THC.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.THC.model.Location;
import com.org.THC.service.LocationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/location")
public class LocationController {

    private LocationService locationService;



    @Autowired
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }

    @GetMapping("/addLocation")
    @ApiOperation(value = "Location created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Created")
    })
    public String addWeatherReading() {
        return "location/addLocation";
    }


    @PostMapping("/addLocation")
    @ApiOperation(value = "Location created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Created")
    })
    public String addWeatherReading(@ModelAttribute("name") String name,@ModelAttribute("addressline1") String addressline1,@ModelAttribute("addressline2") String addressline2,@ModelAttribute("city") String city,@ModelAttribute("state") String state,@ModelAttribute("zip") int zip) {
        locationService.createLocations(name,addressline1,addressline2,city,state,zip);
        return "redirect:/location/getLocations";
    }

    @GetMapping("/getLocations")
    @ApiOperation(value = "Get All locations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Locations Fetched")
    })
    public String getAll(ModelMap modelMap){
        List<Location> locationList = locationService.getAllLocations();
        modelMap.put("Locations",locationList);
        return "location/listLocation";
    }

    @PostMapping("/editLocation")
    @ApiOperation(value = "Get All locations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Locations Fetched")
    })
    public String editLocation(@ModelAttribute("id") String id,ModelMap modelMap){
        Location location= locationService.getLocationsById(id);
        modelMap.put("Locations",location);
        return "location/editLocation";
    }

    @PostMapping("/updateLocation")
    @ApiOperation(value = "Cancel locations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Canceled by Id")
    })
    public String updateLocation(@ModelAttribute("id") String id,@ModelAttribute("name") String name,@ModelAttribute("addressline1") String addressline1,@ModelAttribute("addressline2") String addressline2,@ModelAttribute("city") String city,@ModelAttribute("state") String state,@ModelAttribute("zip") int zip,@ModelAttribute("status") String status){
        locationService.updateLocation(id,name,addressline1,addressline2,city,state,zip,status);
        return "redirect:/location/getLocations";
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
    public String cancelLocation(@ModelAttribute("id") String id){
        locationService.cancelLocation(id);
        return "redirect:/location/getLocations";
    }

    @PostMapping("/activeLocationById")
    @ApiOperation(value = "Cancel locations by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Location Canceled by Id")
    })
    public String activeLocation(@ModelAttribute("id") String id){
        locationService.activeLocation(id);
        return "redirect:/location/getLocations";
    }


}