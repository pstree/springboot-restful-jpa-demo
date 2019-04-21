package com.example.demojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableKafka
@RestController
@SpringBootApplication
public class DemoJpaApplication {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoJpaApplication.class, args);
    }


    @GetMapping("/")
    public String ss() {
        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send("springboot", "ssssss");
        }
        return "ss";
    }

    @KafkaListener(topics = "springboot",groupId = "1")
    public void processMessage(String content) {
        System.out.println("1111111111"+content);
        //kafkaTemplate.send("springboot", "ssssss");
    }

    @KafkaListener(topics = "springboot",groupId = "1")
    public void processMessage1(String content) {
        System.out.println("2222222222"+content);
        //kafkaTemplate.send("springboot", "ssssss");
    }
}