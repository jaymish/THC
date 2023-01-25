package com.org.THC.controller;

import com.org.THC.THCApplication;
import com.org.THC.model.OpenHours;
import com.org.THC.model.PageLocation;
import com.org.THC.model.PageOpenHours;
import com.org.THC.service.OpenHoursService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/open-hours")
public class OpenHoursController {

    private OpenHoursService openHoursService;
    //private static final Logger logger = LogManager.getLogger(THCApplication.class);
    public OpenHoursController(OpenHoursService openHoursService){
        this.openHoursService = openHoursService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create OpenHours received from client service for the location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OpenHours Saved for the location Fetched for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public boolean createOpenHours(@RequestBody OpenHours openHours){
        //logger.info("Controller:User trying to save openHours "+openHours.getDay()+" "+openHours.getStartTime()+" "+openHours.getEndTime());
        openHoursService.createOpenHours(openHours);
        return true;
    }


    @GetMapping(path = "/get-all")
    @Operation(summary = "Get All openHours")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All OpenHours Fetched for the location Fetched for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public List<OpenHours> getOpenHours(@RequestParam("locationid") String locationid){
        List<OpenHours> openHoursList=openHoursService.getAllOpenHours(locationid);
        return openHoursList;
    }

    @PostMapping("/get-by-id")
    @Operation(summary = "Get openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OpenHours Fetched by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public OpenHours openHoursById(@RequestBody String id){
        return openHoursService.getOpenHoursById(id);
    }


    @PostMapping("/deactivate")
    @Operation(summary = "Deactivate openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OpenHours Deactivated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public ResponseEntity<OpenHours> deactivateOpenHours(@RequestBody String id){
        //logger.info("Controller:User trying to deactivate openHours with id: "+id);
        return ResponseEntity.ok(openHoursService.deactivateOpenHours(id));
    }

    @PostMapping("/activate")
    @Operation(summary = "Activate openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OpenHours Deactivated by Id for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public OpenHours activateOpenHours(@RequestBody String id){
        //logger.info("Controller:User trying to activate openHours with id: "+id);
        return openHoursService.activateOpenHours(id);
    }

    @PostMapping("/update")
    @Operation(summary = "Update OpenHours received from client service for the location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OpenHours updated for the location Fetched for client"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public OpenHours updateOpenHours(@RequestBody OpenHours openHours){
        //logger.info("Controller:User trying to update and save openHours with id: "+openHours.getId());
        return openHoursService.updateOpenHours(openHours);
    }

    @GetMapping("/page")
    @Operation(summary = "Get All OpenHours for the location page-vise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All OpenHours for the location Fetched for client page-vise"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public PageOpenHours getAllLocation(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,@RequestParam("locationid") String locationid){
        return openHoursService.getAllpage(pageNo, pageSize, sortBy,locationid);
    }
}
