package com.org.THC.service;


import com.org.THC.model.Orders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {


    private final KafkaTemplate<String, Orders> ordersKafkaTemplate;


    @Value("${kafka.topic.json.name}")
    private String JSON_TOPIC;

    public KafkaProducerService(KafkaTemplate<String, Orders> ordersKafkaTemplate) {

        this.ordersKafkaTemplate = ordersKafkaTemplate;
    }






    public void sendMessageJson(Orders orders){
        log.info(String.format("$$$$ => Consumed Message: %s", orders));

        ordersKafkaTemplate.executeInTransaction(t -> {
            t.send(JSON_TOPIC, orders.getId(), orders);

            return true;
        });
    }

}
