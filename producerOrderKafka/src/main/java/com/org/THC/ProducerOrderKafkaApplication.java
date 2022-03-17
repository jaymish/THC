package com.org.THC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProducerOrderKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerOrderKafkaApplication.class, args);
    }

}
