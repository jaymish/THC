package com.org.THC.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.THC.ProducerOrderKafkaApplication;
import com.org.THC.model.Orders;
import com.org.THC.repo.OrderRepository;
import com.org.THC.service.AutoKafkaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Order")
public class OrderController {

    //private KafkaProducerService kafkaProducerService;
    private OrderRepository orderRepository;
    private AutoKafkaService autoKafkaService;
    private static final Logger logger = LogManager.getLogger(ProducerOrderKafkaApplication.class);



    @Autowired
    public OrderController(AutoKafkaService autoKafkaService,OrderRepository orderRepository){
        this.orderRepository=orderRepository;
        this.autoKafkaService=autoKafkaService;
    }


    @PostMapping("/CreateOrder")
    @Operation(summary = "Order created by Client and saved on the temporary memory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order Created Successfully"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public boolean createOrder(@RequestBody Orders orders) throws JsonProcessingException {
        logger.info("Order saved in temp memory with id:"+orders.getId());
        orderRepository.save(orders);
        return true;
    }


    @GetMapping("/send")
    @Operation(summary = "Order from temporary memory sent to kafka")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order Sent Successfully"),
            @ApiResponse(responseCode = "404", description = "Error page not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    public boolean send(){
        logger.info("All orders in temp memory will be sent to store permanently");
        autoKafkaService.autoKafka();
        return true;
    }


}
