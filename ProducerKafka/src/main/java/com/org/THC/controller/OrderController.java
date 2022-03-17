package com.org.THC.controller;


import com.org.THC.model.Orders;
//import com.org.THC.service.KafkaProducerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/Order")
public class OrderController {

    /*private KafkaProducerService kafkaProducerService;



    @Autowired
    public OrderController(KafkaProducerService kafkaProducerService){
        this.kafkaProducerService=kafkaProducerService;
    }*/


    @PostMapping("/CreateOrder")
    @ApiOperation(value = "Order created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Created")
    })
    public boolean createOrder(@RequestBody Orders orders) {
        System.out.println(orders);
        //kafkaProducerService.sendMessageJson(orders);
        return true;
    }

}
