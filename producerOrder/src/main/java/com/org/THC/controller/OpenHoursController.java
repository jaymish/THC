package com.org.THC.controller;

import com.org.THC.model.OpenHours;
import com.org.THC.service.LocationService;
import com.org.THC.service.OpenHoursService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/open-hours")
public class OpenHoursController {

    private OpenHoursService openHoursService;
    private LocationService locationService;



    @Autowired
    public OpenHoursController(OpenHoursService openHoursService, LocationService locationService){
        this.openHoursService = openHoursService;
        this.locationService=locationService;
    }

    @GetMapping("/add-open-hours")
    @ApiOperation(value = "OpenHours created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Created")
    })
    public String addHours(@ModelAttribute("location") String locationId, ModelMap modelMap) {

        modelMap.put("LocationId",locationId);
        return "openHours/addOpenHours";
    }


    @PostMapping("/add-open-hours")
    @ApiOperation(value = "OpenHours created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Created")
    })
    public String addHours(@ModelAttribute("day") String day, @ModelAttribute("startTime") String startTime, @ModelAttribute("endTime") String endTime,  @ModelAttribute("location") String locationId,ModelMap modelMap) {
        openHoursService.createOpenHours(day,startTime,endTime,locationId);
        modelMap.put("locationid",locationId);
        return "redirect:/open-hours/get-open-hours?locationid="+locationId+"&deactivate=";
    }

    @GetMapping("/get-open-hours")
    @ApiOperation(value = "Get All openHours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All OpenHours Fetched")
    })
    public String getAll(@ModelAttribute("locationid") String id,ModelMap modelMap){
        List<OpenHours> openHoursList = openHoursService.getAllOpenHours(id);

        modelMap.put("LocationOpenHours",openHoursList);
        modelMap.put("locationid",id);
        return "openHours/listOpenHours";
    }

    @PostMapping("/edit-open-hours")
    @ApiOperation(value = "Get All openHours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All OpenHours Fetched")
    })
    public String editOpenHours(@ModelAttribute("id") String id,ModelMap modelMap){
        OpenHours openHours= openHoursService.getOpenHoursById(id);

        modelMap.put("OpenHours",openHours);
        return "openHours/editOpenHours";
    }

    @PostMapping("/update-open-hours")
    @ApiOperation(value = "Cancel openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Deactivated by Id")
    })
    public String updateOpenHours(@ModelAttribute("id") String id,@ModelAttribute("day") String day, @ModelAttribute("startTime") String startTime, @ModelAttribute("endTime") String endTime,@ModelAttribute("location") String location){
        openHoursService.updateOpenHours(id,day,startTime,endTime,location);
        return "redirect:/open-hours/get-open-hours?locationid="+location+"&deactivate=";
    }


    /*@PostMapping("/get-open-hoursById")
    @ApiOperation(value = "Get openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Fetched by Id")
    })
    public OpenHours getById(@RequestBody String id){
        return openHoursService.getOpenHoursById(id);
    }

    @PostMapping("/get-open-hoursByZip")
    @ApiOperation(value = "Get openHours by ZipCode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Fetched by Zip")
    })
    public List<OpenHours> getOpenHoursByZip(@RequestBody int zipcode){
        return openHoursService.getOpenHoursByZip(zipcode);
    }*/

    @PostMapping("/deactivate-open-hours-by-id")
    @ApiOperation(value = "Cancel openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Deactivated by Id")
    })
    public String deactivateOpenHours(@ModelAttribute("id") String id){
        OpenHours openHours= openHoursService.deactivateOpenHours(id);
        return "redirect:/open-hours/get-open-hours?locationid="+openHours.getLocation().getId()+"&deactivate=";

    }

    @PostMapping("/activate-open-hours-by-id")
    @ApiOperation(value = "Cancel openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Deactivated by Id")
    })
    public String activateOpenHours(@ModelAttribute("id") String id){
        OpenHours openHours= openHoursService.activateOpenHours(id);
        return "redirect:/open-hours/get-open-hours?locationid="+openHours.getLocation().getId()+"&deactivate=";
    }
}
