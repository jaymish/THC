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
@RequestMapping(path = "/openHours")
public class OpenHoursController {

    private OpenHoursService openHoursService;
    private LocationService locationService;



    @Autowired
    public OpenHoursController(OpenHoursService openHoursService, LocationService locationService){
        this.openHoursService = openHoursService;
        this.locationService=locationService;
    }

    @GetMapping("/addOpenHours")
    @ApiOperation(value = "OpenHours created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Created")
    })
    public String addWeatherReading(@ModelAttribute("location") String locationId, ModelMap modelMap) {

        modelMap.put("LocationId",locationId);
        return "addOpenHours";
    }


    @PostMapping("/addOpenHours")
    @ApiOperation(value = "OpenHours created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Created")
    })
    public String addWeatherReading(@ModelAttribute("day") String day, @ModelAttribute("startTime") String startTime, @ModelAttribute("endTime") String endTime,  @ModelAttribute("location") String locationId,ModelMap modelMap) {
        openHoursService.createOpenHours(day,startTime,endTime,locationId);
        modelMap.put("locationid",locationId);
        return "redirect:/openHours/getOpenHours?locationid="+locationId+"&deactivate=";
    }

    @GetMapping("/getOpenHours")
    @ApiOperation(value = "Get All openHours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All OpenHours Fetched")
    })
    public String getAll(@ModelAttribute("locationid") String id,ModelMap modelMap){
        List<OpenHours> openHoursList = openHoursService.getAllOpenHours(id);

        modelMap.put("LocationOpenHours",openHoursList);
        modelMap.put("locationid",id);
        System.out.println(openHoursList);
        return "openHours";
    }

    @PostMapping("/editOpenHours")
    @ApiOperation(value = "Get All openHours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All OpenHours Fetched")
    })
    public String editOpenHours(@ModelAttribute("id") String id,ModelMap modelMap){
        OpenHours openHours= openHoursService.getOpenHoursById(id);

        modelMap.put("OpenHours",openHours);
        System.out.println(openHours);
        System.out.println("I am here");
        return "editOpenHours";
    }

    @PostMapping("/updateOpenHours")
    @ApiOperation(value = "Cancel openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Canceled by Id")
    })
    public String updateOpenHours(@ModelAttribute("id") String id,@ModelAttribute("day") String day, @ModelAttribute("startTime") String startTime, @ModelAttribute("endTime") String endTime,@ModelAttribute("location") String location){
        //openHoursService.activeOpenHours(id);
        System.out.println("openHours here");
        //openHours.setLocation(location);
        openHoursService.updateOpenHours( id,day,   startTime, endTime, location);
        System.out.println(location);
        System.out.println("openHours here");
        return "redirect:/openHours/getOpenHours?locationid="+location+"&deactivate=";
    }


    @PostMapping("/getOpenHoursById")
    @ApiOperation(value = "Get openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Fetched by Id")
    })
    public OpenHours getById(@RequestBody String id){
        return openHoursService.getOpenHoursById(id);
    }

    @PostMapping("/getOpenHoursByZip")
    @ApiOperation(value = "Get openHours by ZipCode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Fetched by Zip")
    })
    public List<OpenHours> getOpenHoursByZip(@RequestBody int zipcode){
        return openHoursService.getOpenHoursByZip(zipcode);
    }

    @PostMapping("/cancelOpenHoursById")
    @ApiOperation(value = "Cancel openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Canceled by Id")
    })
    public String cancelOpenHours(@ModelAttribute("id") String id){
        OpenHours openHours= openHoursService.cancelOpenHours(id);
        return "redirect:/openHours/getOpenHours?locationid="+openHours.getLocation().getId()+"&deactivate=";

    }

    @PostMapping("/activeOpenHoursById")
    @ApiOperation(value = "Cancel openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Canceled by Id")
    })
    public String activeOpenHours(@ModelAttribute("id") String id){
        OpenHours openHours= openHoursService.activeOpenHours(id);
        return "redirect:/openHours/getOpenHours?locationid="+openHours.getLocation().getId()+"&deactivate=";
    }
}
