package com.order.producerorder.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.producerorder.model.Orders;
import com.order.producerorder.repo.OrderRepository;
import com.order.producerorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {
    //List<Weather> list=new LinkedList<>();

    private OrderRepository orderRepository;
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private List<Orders> ordersList;

    @Autowired
    public DefaultOrderService(OrderRepository orderRepository, RestTemplate restTemplate, ObjectMapper objectMapper, List<Orders> ordersList){
        this.orderRepository = orderRepository;
        this.restTemplate=restTemplate;
        this.objectMapper=objectMapper;
        this.ordersList=ordersList;
    }

    @Override
    public boolean createOrders(Orders orders) throws JsonProcessingException {

        return restTemplate.postForObject("http://localhost:8081/createOrder", orders, boolean.class);

    }

    @Override
    public List<Orders> getAllOrders() {
        //List<Orders> ordersList= (List<Orders>) orderRepository.findAll();
        return restTemplate.getForObject("http://localhost:8081/getOrder", ordersList.getClass());
    }

    @Override
    public Orders getOrdersById(String id) {
        return restTemplate.postForObject("http://localhost:8081/getById", id,Orders.class);
    }

    @Override
    public List<Orders> getOrdersByZip(int zip) {
        return restTemplate.postForObject("http://localhost:8081/getByzip", zip, ordersList.getClass());
    }

    @Override
    public Orders cancelOrder(String id) {
        return restTemplate.postForObject("http://localhost:8081/cancel", id,Orders.class);
    }
}
