package com.org.THC.service;


import com.org.THC.ProducerOrderKafkaApplication;
import com.org.THC.model.Orders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {


    private final KafkaTemplate<String, Orders> ordersKafkaTemplate;
    // //private static final Logger logger = LogManager.getLogger(ProducerOrderKafkaApplication.class);


    @Value("${kafka.topic.json.name}")
    private String JSON_TOPIC;

    public KafkaProducerService(KafkaTemplate<String, Orders> ordersKafkaTemplate) {

        this.ordersKafkaTemplate = ordersKafkaTemplate;
    }






    public void sendMessageJson(Orders orders){
        // //logger.info("Order with id:"+orders.getId()+" sent to kafka");

        ordersKafkaTemplate.executeInTransaction(t -> {
            t.send(JSON_TOPIC, orders.getId(), orders);

            return true;
        });
    }

}
