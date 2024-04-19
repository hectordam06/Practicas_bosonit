package com.bosonit.BackWeb_TPV.config;

import com.bosonit.BackWeb_TPV.domain.Autobus;
import com.bosonit.BackWeb_TPV.repositories.AutobusRepository;

import org.apache.kafka.clients.consumer.ConsumerConfig;

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
public class KafkaConsumer {
    @Autowired
    AutobusRepository autobusRepository;
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;
    public Map<String, Object> consumerConfigs(){
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, true);
        return props;
    }
    @Bean
    ConsumerFactory<String, Autobus> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(),  new StringDeserializer(), new JsonDeserializer<>(Autobus.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Autobus> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Autobus> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    @KafkaListener(topics = "Autobuses")

    public void listenReservasDisponibles(Autobus autobus) {
        autobusRepository.save(autobus);
    }

}
