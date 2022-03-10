package com.org.THC.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.THC.model.Location;
import com.org.THC.service.LocationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/weather")
public class LocationController {

    private LocationService locationService;



    @Autowired
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }


    @PostMapping("/addReading")
    @ApiOperation(value = "Order created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Created")
    })
    public boolean addWeatherReading(@RequestBody Location location) throws JsonProcessingException {
        System.out.println(location);
        locationService.createOrders(location);
        return true;
    }

    @GetMapping("/getOrders")
    @ApiOperation(value = "Get All orders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Orders Fetched")
    })
    public List<Location> getAll(){
        return locationService.getAllOrders();
    }

    @PostMapping("/getOrderById")
    @ApiOperation(value = "Get orders by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Fetched by Id")
    })
    public Location getById(@RequestBody String id){
        return locationService.getOrdersById(id);
    }

    @PostMapping("/getOrderByZip")
    @ApiOperation(value = "Get orders by ZipCode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Fetched by Zip")
    })
    public List<Location> getOrdersByZip(@RequestBody int zipcode){
        return locationService.getOrdersByZip(zipcode);
    }

    @PostMapping("/cancelOrderById")
    @ApiOperation(value = "Cancel orders by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Canceled by Id")
    })
    public Location cancelOrder(@RequestBody String id){
        return locationService.cancelOrder(id);
    }




}
