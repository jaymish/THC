package com.org.THC.controller;

import com.org.THC.model.OpenHours;
import com.org.THC.model.PageMenu;
import com.org.THC.model.PageOpenHours;
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

    @GetMapping("/add")
    @ApiOperation(value = "OpenHours created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Created")
    })
    public String addHours(@RequestParam("location") String locationId, ModelMap modelMap) {

        modelMap.put("LocationId",locationId);
        return "openHours/addOpenHours";
    }


    @PostMapping("/add")
    @ApiOperation(value = "OpenHours created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Created")
    })
    public String addHours(@ModelAttribute("day") String day, @ModelAttribute("startTime") String startTime, @ModelAttribute("endTime") String endTime,  @ModelAttribute("location") String locationId,ModelMap modelMap) {
        openHoursService.createOpenHours(day,startTime,endTime,locationId);
        modelMap.put("locationid",locationId);
        return "redirect:/open-hours/get-all?locationid="+locationId+"&deactivate=";
    }

    @GetMapping("/get-all")
    @ApiOperation(value = "Get All openHours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All OpenHours Fetched")
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
    @ApiOperation(value = "Get All openHours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All OpenHours Fetched")
    })
    public String editOpenHours(@ModelAttribute("id") String id,ModelMap modelMap){
        OpenHours openHours= openHoursService.getOpenHoursById(id);

        modelMap.put("OpenHours",openHours);
        return "openHours/editOpenHours";
    }

    @PostMapping("/update")
    @ApiOperation(value = "Cancel openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Deactivated by Id")
    })
    public String updateOpenHours(@ModelAttribute("id") String id,@ModelAttribute("day") String day, @ModelAttribute("startTime") String startTime, @ModelAttribute("endTime") String endTime,@ModelAttribute("location") String location){
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
    @ApiOperation(value = "Cancel openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Deactivated by Id")
    })
    public String deactivateOpenHours(@ModelAttribute("id") String id){
        OpenHours openHours= openHoursService.deactivateOpenHours(id);
        return "redirect:/open-hours/get-all?locationid="+openHours.getLocation().getId()+"&deactivate=";

    }

    @PostMapping("/activate-by-id")
    @ApiOperation(value = "Cancel openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Deactivated by Id")
    })
    public String activateOpenHours(@ModelAttribute("id") String id){
        OpenHours openHours= openHoursService.activateOpenHours(id);
        return "redirect:/open-hours/get-all?locationid="+openHours.getLocation().getId()+"&deactivate=";
    }
}
