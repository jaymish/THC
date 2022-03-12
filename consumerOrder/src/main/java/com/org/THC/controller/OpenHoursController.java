package com.org.THC.controller;

import com.org.THC.model.OpenHours;
import com.org.THC.service.OpenHoursService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/openHours")
public class OpenHoursController {

    private OpenHoursService openHoursService;
    public OpenHoursController(OpenHoursService openHoursService){
        this.openHoursService = openHoursService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create OpenHours received from client service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Saved on DB")
    })
    public boolean createOpenHours(@RequestBody OpenHours openHours){
        openHoursService.createOpenHours(openHours);
        return true;
    }


    @PostMapping(path = "/getAll")
    @ApiOperation(value = "Get All openHours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All OpenHours Fetched for client")
    })
    public List<OpenHours> getOpenHours(@RequestBody String locationid){
        List<OpenHours> openHoursList=openHoursService.getAllOpenHours(locationid);
        return openHoursList;
    }

    @PostMapping("/getById")
    @ApiOperation(value = "Get openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Fetched by Id for client" )
    })
    public OpenHours openHoursById(@RequestBody String id){
        return openHoursService.getOpenHoursById(id);
    }


    @PostMapping("/cancel")
    @ApiOperation(value = "Cancel openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Canceled by Id for client")
    })
    public ResponseEntity<OpenHours> cancelOpenHours(@RequestBody String id){
        return ResponseEntity.ok(openHoursService.cancelOpenHours(id));
    }

    @PostMapping("/active")
    @ApiOperation(value = "Cancel openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Canceled by Id for client")
    })
    public OpenHours activeOpenHours(@RequestBody String id){
        return openHoursService.activeOpenHours(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "Cancel openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Canceled by Id for client")
    })
    public OpenHours updateOpenHours(@RequestBody OpenHours openHours){
        return openHoursService.updateOpenHours(openHours);
    }
}
