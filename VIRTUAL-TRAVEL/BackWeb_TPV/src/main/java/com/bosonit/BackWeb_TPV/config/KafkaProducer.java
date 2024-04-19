package com.bosonit.BackWeb_TPV.config;

import com.bosonit.BackWeb_TPV.controller.dto.ReservaInputDto;
import com.bosonit.BackWeb_TPV.controller.dto.ReservaOutputDto;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducer {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String address;

    @Bean
    public ProducerFactory<String, ReservaOutputDto> reservaInputDtoProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                address);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, ReservaOutputDto> reservaInputDtoKafkaTemplate() {
        return new KafkaTemplate<>(reservaInputDtoProducerFactory());
    }

    public void enviarReserva(ReservaOutputDto reservaInputDto) {
        reservaInputDtoKafkaTemplate().send("reservas-topic", reservaInputDto);
    }
}
