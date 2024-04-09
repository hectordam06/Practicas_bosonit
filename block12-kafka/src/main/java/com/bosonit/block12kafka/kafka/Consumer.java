package com.bosonit.block12kafka.kafka;

import com.bosonit.block12kafka.domain.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    ObjectMapper mapper;

@KafkaListener(topics = "springtopic")
    public  void onMessege(ConsumerRecord<String,String>messege) throws JsonProcessingException {
       String customerJson= messege.value();
      Customer customer= mapper.readValue(customerJson,Customer.class);
        System.out.println(customer);
  }
}
/*  @KafkaListener(topics = "${message.topic.name:profesorp}", groupId = "${message.group.name:profegroup}")
    public void listenTopic1(String message) {
        System.out.println("Recieved Message of topic1 in  listener: " + message);
    }

    @KafkaListener(topics = "${message.topic.name2:profesorp-group}", groupId = "${message.group.name:profegroup}")
    public void listenTopic2(String message) {
        System.out.println("Recieved Message of topic2 in  listener "+message);
    }*/