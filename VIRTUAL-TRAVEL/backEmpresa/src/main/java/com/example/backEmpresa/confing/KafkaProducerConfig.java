package com.example.backEmpresa.confing;


import com.example.backEmpresa.repositories.AutobusRepository;


import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.virtualtravel.common.Autobus;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Autowired
    AutobusRepository autobusRepository;
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String address;
    @Bean
    public ProducerFactory<String, Autobus> reservaOutputDtoProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                address);
        configProps.put(
                org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
          org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<String,Autobus> autobusOutputDtoKafkaTemplate(){
        return new KafkaTemplate<>(reservaOutputDtoProducerFactory());
    }
    public void enviarAutobus(){
        autobusRepository.findAll().forEach(t ->{
            System.out.println(t);autobusOutputDtoKafkaTemplate().send("Autobuses",t.transform());});

    }
}
