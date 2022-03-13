package com.org.THC.controller;

import com.org.THC.model.OpenHours;
import com.org.THC.model.PageLocation;
import com.org.THC.model.PageOpenHours;
import com.org.THC.service.OpenHoursService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/open-hours")
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


    @GetMapping(path = "/get-all")
    @ApiOperation(value = "Get All openHours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All OpenHours Fetched for client")
    })
    public List<OpenHours> getOpenHours(@RequestParam("locationid") String locationid){
        List<OpenHours> openHoursList=openHoursService.getAllOpenHours(locationid);
        return openHoursList;
    }

    @PostMapping("/get-by-id")
    @ApiOperation(value = "Get openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Fetched by Id for client" )
    })
    public OpenHours openHoursById(@RequestBody String id){
        return openHoursService.getOpenHoursById(id);
    }


    @PostMapping("/deactivate")
    @ApiOperation(value = "Deactivate openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Deactivateed by Id for client")
    })
    public ResponseEntity<OpenHours> deactivateOpenHours(@RequestBody String id){
        return ResponseEntity.ok(openHoursService.deactivateOpenHours(id));
    }

    @PostMapping("/activate")
    @ApiOperation(value = "Deactivate openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Deactivateed by Id for client")
    })
    public OpenHours activateOpenHours(@RequestBody String id){
        return openHoursService.activateOpenHours(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "Deactivate openHours by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OpenHours Deactivateed by Id for client")
    })
    public OpenHours updateOpenHours(@RequestBody OpenHours openHours){
        return openHoursService.updateOpenHours(openHours);
    }

    @GetMapping("/page")
    public PageOpenHours getAllLocation(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,@RequestParam("locationid") String locationid){
        return openHoursService.getAllpage(pageNo, pageSize, sortBy,locationid);
    }
}
