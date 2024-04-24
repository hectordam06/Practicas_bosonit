package com.example.backEmpresa.confing;

import com.example.backEmpresa.controller.AutobusController;
import com.example.backEmpresa.controller.dto.ReservaOutputDto;

import com.virtualtravel.common.Reserva;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Autowired
    AutobusController autobusController;
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;
    public Map<String, Object> consumerConfigs(){
        Map<String, Object> props = new HashMap<>();
        props.put(
                org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG,
                groupId);
        props.put(
                org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, true);
        return props;
    }
    @Bean
    ConsumerFactory<String, Reserva> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(),  new StringDeserializer(), new JsonDeserializer<>(Reserva.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Reserva> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Reserva> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    @KafkaListener(topics = "reservas-topic")

    public void listenReservasDisponibles(Reserva reservaOutputDto) {
        autobusController.confirmarReserva(reservaOutputDto);
    }

}


