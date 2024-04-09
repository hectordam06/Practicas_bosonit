package com.bosonit.block12kafka.controller;

import com.bosonit.block12kafka.kafka.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Customercontroller {
    @Autowired
    Producer producer;
   @GetMapping("/send")
    public void sendMessege() throws JsonProcessingException {
        producer.sendCustomer();
    }
}
