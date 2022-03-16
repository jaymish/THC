package com.org.THC.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.THC.model.Order;
import com.org.THC.repo.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {
    private OrderRepo orderRepo;

    public ConsumerService(OrderRepo orderRepo){
        this.orderRepo=orderRepo;
    }

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.json.name}",
            groupId = "${kafka.topic.json.groupId}")
    public void consumeCustomerData(Order order) throws JsonProcessingException {
        log.info("Consumed Message: {}, {}", order.getId(), order);
        orderRepo.save(order);
    }

}
