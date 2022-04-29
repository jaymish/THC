package com.org.THC.service;

import com.org.THC.ProducerOrderKafkaApplication;
import com.org.THC.model.Orders;
import com.org.THC.repo.OrderRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AutoKafkaService {

    private KafkaProducerService kafkaProducerService;
    private OrderRepository orderRepository;
    private static final Logger logger = LogManager.getLogger(ProducerOrderKafkaApplication.class);

    public AutoKafkaService(KafkaProducerService kafkaProducerService,OrderRepository orderRepository){
        this.kafkaProducerService=kafkaProducerService;
        this.orderRepository=orderRepository;
    }


    @Transactional
    //@Scheduled(fixedRate = 60000)
    public boolean autoKafka(){
        Iterable<Orders> ordersList=orderRepository.findAll();
        for(Orders orders: ordersList){
            logger.info("Order with id:"+orders.getId()+" sent to kafka to store permanently");
            kafkaProducerService.sendMessageJson(orders);
            orderRepository.deleteAllById(orders.getId());
        }
        System.out.println("schedule");
        return true;
    }
}
