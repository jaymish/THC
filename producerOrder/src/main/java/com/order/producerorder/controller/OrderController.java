package com.order.producerorder.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.producerorder.model.Orders;
import com.order.producerorder.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/weather")
public class OrderController {

    private OrderService orderService;



    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }


    @PostMapping("/addReading")
    @ApiOperation(value = "Order created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Created")
    })
    public boolean addWeatherReading(@RequestBody Orders orders) throws JsonProcessingException {
        System.out.println(orders);
        orderService.createOrders(orders);
        return true;
    }

    @GetMapping("/getOrders")
    @ApiOperation(value = "Get All orders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Orders Fetched")
    })
    public List<Orders> getAll(){
        return orderService.getAllOrders();
    }

    @PostMapping("/getOrderById")
    @ApiOperation(value = "Get orders by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Fetched by Id")
    })
    public Orders getById(@RequestBody String id){
        return orderService.getOrdersById(id);
    }

    @PostMapping("/getOrderByZip")
    @ApiOperation(value = "Get orders by ZipCode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Fetched by Zip")
    })
    public List<Orders> getOrdersByZip(@RequestBody int zipcode){
        return orderService.getOrdersByZip(zipcode);
    }

    @PostMapping("/cancelOrderById")
    @ApiOperation(value = "Cancel orders by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Canceled by Id")
    })
    public Orders cancelOrder(@RequestBody String id){
        return orderService.cancelOrder(id);
    }




}
