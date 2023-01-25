package com.org.THC.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.THC.THCApplication;
import com.org.THC.model.Location;
import com.org.THC.model.PageLocation;
import com.org.THC.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/location")
public class LocationController {

    private LocationService locationService;
    //private static final Logger logger = LogManager.getLogger(LocationController.class);



    @Autowired
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }

    @GetMapping("/add")
    @Operation(summary = "Add new Location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Add Location requested"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String addLocation() {
        return "location/addLocation";
    }


    @PostMapping("/add")
    @Operation(summary = "Create Location received by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location Saved"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String addLocation(@ModelAttribute("Location") Location location /*@ModelAttribute("name") String name,@ModelAttribute("addressline1") String addressline1,@ModelAttribute("addressline2") String addressline2,@ModelAttribute("city") String city,@ModelAttribute("state") String state,@ModelAttribute("zip") int zip*/) {
        //logger.info("Controller:User trying to save location"+name+" "+addressline1);

        locationService.createLocations(location.getName(),location.getAddressline1(),location.getAddressline2(),location.getCity(),location.getState(),location.getZip());
        //locationService.createLocations(name,addressline1,addressline2,city,state,zip);
        return "redirect:/location/get-all";
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get All locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Locations Fetched"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String getAll(@RequestParam(defaultValue = "all") String show,@RequestParam(defaultValue = "0") Integer pageNo,@RequestParam(defaultValue = "5") Integer pageSize,@RequestParam(defaultValue = "") String message,ModelMap modelMap){
        //List<Location> locationList = locationService.getAllLocations();
        PageLocation pageLocation=locationService.getAllpage(pageNo,pageSize,show);
        modelMap.put("Locations",pageLocation.getLocationList());
        if(pageLocation.getPages()<=0){
            modelMap.put("pages",0);
        }
        else {
            modelMap.put("pages", pageLocation.getPages() - 1);
        }
        modelMap.put("currentpage",pageNo);
        modelMap.put("show",show);
        modelMap.put("message",message);
        return "location/listLocation";
    }

    @PostMapping("/edit")
    @Operation(summary = "Edit Location Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated location sent to save"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String editLocation(@ModelAttribute("id") String id,ModelMap modelMap){
        Location location= locationService.getLocationsById(id);
        modelMap.put("Locations",location);
        //logger.info("Controller:User trying to edit location with id: "+id);
        return "location/editLocation";
    }

    @PostMapping("/update")
    @Operation(summary = "Update Location Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated location details Successfully"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String updateLocation(@ModelAttribute("id") String id,@ModelAttribute("name") String name,@ModelAttribute("addressline1") String addressline1,@ModelAttribute("addressline2") String addressline2,@ModelAttribute("city") String city,@ModelAttribute("state") String state,@ModelAttribute("zip") int zip,@ModelAttribute("status") String status){
        //logger.info("Controller:User trying to update and save location with id: "+id);
        locationService.updateLocation(id,name,addressline1,addressline2,city,state,zip,status);
        return "redirect:/location/get-all";
    }

    @PostMapping("/deactivate-by-id")
    @Operation(summary = "Deactivate locations by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location Deactivated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String deactivateLocation(@ModelAttribute("id") String id,@ModelAttribute("show") String show){
        //logger.info("Controller:User trying to deactivate location with id: "+id);
        locationService.deactivateLocation(id);
        return "redirect:/location/get-all?show="+show;
    }

    @PostMapping("/activate-by-id")
    @Operation(summary = "Activate locations by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location Activated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String activateLocation(@ModelAttribute("id") String id,@ModelAttribute("show") String show){
        //logger.info("Controller:User trying to activate location with id: "+id);
        locationService.activateLocation(id);
        return "redirect:/location/get-all?show="+show;
    }


}
