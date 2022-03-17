package com.org.THC.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.THC.model.Orders;
import com.org.THC.repo.OrderRepository;
import com.org.THC.service.KafkaProducerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Order")
public class OrderController {

    private KafkaProducerService kafkaProducerService;
    private OrderRepository orderRepository;



    @Autowired
    public OrderController(KafkaProducerService kafkaProducerService,OrderRepository orderRepository){
        this.kafkaProducerService = kafkaProducerService;
        this.orderRepository=orderRepository;
    }


    @PostMapping("/CreateOrder")
    @ApiOperation(value = "Order created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Created")
    })
    public boolean createOrder(@RequestBody Orders orders) throws JsonProcessingException {
        System.out.println(orders);
        orderRepository.save(orders);
        System.out.println(orders);
        //kafkaProducerService.sendMessageJson(orders);
        return true;
    }



}
