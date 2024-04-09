package com.bosonit.block12kafka.kafka;

import com.bosonit.block12kafka.domain.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class Producer {
    @Autowired
  private KafkaTemplate<String,String> kafka ;
    @Autowired
    ObjectMapper mapper;
/*
    public void sendString(){
        kafka.sendDefault("hello world");
    }*/
    public void sendCustomer() throws JsonProcessingException {
        Customer customer = new Customer(1l,"pepe",100.0);
        String json= mapper.writeValueAsString(customer);
        kafka.sendDefault(json);
    }
}
/*  @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${message.topic.name:profesorp}")
    private String topicName;

    public void sendMessage(String topic,String message) {
        if (topic==null || topic.trim().equals(""))
            topic=topicName;
        ListenableFuture<SendResult<String, String>> future = (ListenableFuture<SendResult<String, String>>) kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {System.err.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }*/