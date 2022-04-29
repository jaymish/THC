package com.org.THC.controller;

import com.org.THC.THCApplication;
import com.org.THC.model.OpenHours;
import com.org.THC.model.PageOpenHours;
import com.org.THC.service.LocationService;
import com.org.THC.service.OpenHoursService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(THCApplication.class);



    @Autowired
    public OpenHoursController(OpenHoursService openHoursService, LocationService locationService){
        this.openHoursService = openHoursService;
        this.locationService=locationService;
    }

    @GetMapping("/add")
    @Operation(summary = "Add new openHours")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Add openHours requested"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String addHours(@RequestParam("location") String locationId, ModelMap modelMap) {

        modelMap.put("LocationId",locationId);
        return "openHours/addOpenHours";
    }


    @PostMapping("/add")
    @Operation(summary = "Create openHours received by user for given Location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "openHours Saved"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String addHours(@ModelAttribute("day") String day, @ModelAttribute("startTime") String startTime, @ModelAttribute("endTime") String endTime,  @ModelAttribute("location") String locationId,ModelMap modelMap) {
        logger.info("Controller:User trying to save openHours "+day+" "+startTime+" "+endTime);
        openHoursService.createOpenHours(day,startTime,endTime,locationId);
        modelMap.put("locationid",locationId);
        return "redirect:/open-hours/get-all?locationid="+locationId+"&deactivate=";
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get All openHours for the location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All openHourss Fetched for the location  for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String getAll(@RequestParam(defaultValue = "0") Integer pageNo,@RequestParam(defaultValue = "5") Integer pageSize,@RequestParam("locationid") String id,ModelMap modelMap){
        PageOpenHours pageOpenHours=openHoursService.getAllpage(pageNo,pageSize,id);
        modelMap.put("LocationOpenHours",pageOpenHours.getOpenHoursList());
        if(pageOpenHours.getPages()<=0){
            modelMap.put("pages",0);
        }
        else {
            modelMap.put("pages", pageOpenHours.getPages() - 1);
        }
        modelMap.put("currentpage",pageNo);
        /* List<OpenHours> openHoursList = openHoursService.getAllOpenHours(id);

        modelMap.put("LocationOpenHours",openHoursList);*/
        modelMap.put("locationid",id);
        return "openHours/listOpenHours";
    }

    @PostMapping("/edit")
    @Operation(summary = "Edit openHours Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated openHours sent to save"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String editOpenHours(@ModelAttribute("id") String id,ModelMap modelMap){
        OpenHours openHours= openHoursService.getOpenHoursById(id);
        logger.info("Controller:User trying to edit openHours with id: "+id);
        modelMap.put("OpenHours",openHours);
        return "openHours/editOpenHours";
    }

    @PostMapping("/update")
    @Operation(summary = "Update openHours from client service for the location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "openHours updated for the location Fetched for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String updateOpenHours(@ModelAttribute("id") String id,@ModelAttribute("day") String day, @ModelAttribute("startTime") String startTime, @ModelAttribute("endTime") String endTime,@ModelAttribute("location") String location){
        logger.info("Controller:User trying to update and save openHours with id: "+id);
        openHoursService.updateOpenHours(id,day,startTime,endTime,location);
        return "redirect:/open-hours/get-all?locationid="+location+"&deactivate=";
    }


    /*@PostMapping("/get-allById")
    @ApiOperation(value = "Get openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Fetched by Id")
    })
    public OpenHours getById(@RequestBody String id){
        return openHoursService.getOpenHoursById(id);
    }

    @PostMapping("/get-allByZip")
    @ApiOperation(value = "Get openHours by ZipCode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Fetched by Zip")
    })
    public List<OpenHours> getOpenHoursByZip(@RequestBody int zipcode){
        return openHoursService.getOpenHoursByZip(zipcode);
    }*/

    @PostMapping("/deactivate-by-id")
    @Operation(summary = "Deactivate openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "openHours Deactivated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String deactivateOpenHours(@ModelAttribute("id") String id){
        logger.info("Controller:User trying to deactivate openHours with id: "+id);
        OpenHours openHours= openHoursService.deactivateOpenHours(id);
        return "redirect:/open-hours/get-all?locationid="+openHours.getLocation().getId()+"&deactivate=";

    }

    @PostMapping("/activate-by-id")
    @Operation(summary = "Activate openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "openHours activated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public String activateOpenHours(@ModelAttribute("id") String id){
        logger.info("Controller:User trying to activate openHours with id: "+id);
        OpenHours openHours= openHoursService.activateOpenHours(id);
        return "redirect:/open-hours/get-all?locationid="+openHours.getLocation().getId()+"&deactivate=";
    }
}
