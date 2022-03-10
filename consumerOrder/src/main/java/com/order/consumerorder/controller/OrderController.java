package com.order.consumerorder.controller;


import com.order.consumerorder.model.Orders;
import com.order.consumerorder.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class


OrderController {

    private OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    @ApiOperation(value = "Create Order received from client service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Saved on DB")
    })
    public boolean createOrders(@RequestBody Orders orders){
        orderService.createOrder(orders);
        return true;
    }


    @GetMapping(path = "/getOrder")
    @ApiOperation(value = "Get All orders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Orders Fetched for client")
    })
    public List<Orders> getOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/getById")
    @ApiOperation(value = "Get orders by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Fetched by Id for client" )
    })
    public Orders ordersById(@RequestBody String id){
        return orderService.getOrdersById(id);
    }

    @PostMapping("/getByzip")
    @ApiOperation(value = "Get orders by ZipCode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Fetched by Zip for client")
    })
    public List<Orders> ordersByZip(@RequestBody int zip){
        return orderService.getOrdersByZip(zip);
    }

    @PostMapping("/cancel")
    @ApiOperation(value = "Cancel orders by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Canceled by Id for client")
    })
    public ResponseEntity<Orders> cancelOrder(@RequestBody String id){
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }
}
