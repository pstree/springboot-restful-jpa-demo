package com.example.demojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@EnableCaching
@RestController
@SpringBootApplication
public class DemoJpaApplication {

    @Autowired
    private UserRepository userRepository;



    public static void main(String[] args) {
        SpringApplication.run(DemoJpaApplication.class, args);
    }

    @GetMapping("/get/{id}")
    public User get(@PathVariable("id") Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
