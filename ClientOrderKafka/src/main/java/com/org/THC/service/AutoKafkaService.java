package com.org.THC.service;

import com.org.THC.model.Orders;
import com.org.THC.repo.OrderRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AutoKafkaService {

    private KafkaProducerService kafkaProducerService;
    private OrderRepository orderRepository;

    public AutoKafkaService(KafkaProducerService kafkaProducerService,OrderRepository orderRepository){
        this.kafkaProducerService=kafkaProducerService;
        this.orderRepository=orderRepository;
    }


    @Transactional
    @Scheduled(fixedRate = 60000)
    public void autoKafka(){
        Iterable<Orders> ordersList=orderRepository.findAll();
        for(Orders orders: ordersList){
            System.out.println(orders);
            kafkaProducerService.sendMessageJson(orders);
            orderRepository.deleteAllById(orders.getId());
        }
        System.out.println("schedule");
    }
}
