package com.org.THC.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.THC.THCApplication;
import com.org.THC.model.Orders;
import com.org.THC.repo.OrderRepo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private OrderRepo orderRepo;
    private static final Logger logger = LogManager.getLogger(THCApplication.class);

    public ConsumerService(OrderRepo orderRepo){
        this.orderRepo=orderRepo;
    }

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.json.name}",
            groupId = "${kafka.topic.json.groupId}")
    public void consumeCustomerData(Orders orders) throws JsonProcessingException {
        logger.info("order with id:"+orders.getId()+" received from kafka to store permanently");
        orderRepo.save(orders);
    }

}
