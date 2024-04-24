package com.bosonit.BackWeb_TPV.config;

import com.bosonit.BackWeb_TPV.controller.dto.ReservaInputDto;
import com.bosonit.BackWeb_TPV.controller.dto.ReservaOutputDto;

import com.virtualtravel.common.Reserva;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducer {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String address;

    @Bean
    public ProducerFactory<String, Reserva> reservaInputDtoProducerFactory() {
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
    public KafkaTemplate<String, Reserva> reservaInputDtoKafkaTemplate() {
        return new KafkaTemplate<>(reservaInputDtoProducerFactory());
    }

    public void enviarReserva(ReservaOutputDto reservaOutputDto) {
       Reserva reserva = new Reserva();
       reserva.setId(reservaOutputDto.getId());
       reserva.setNombre(reservaOutputDto.getNombre());
       reserva.setApellidos(reservaOutputDto.getApellidos());
       reserva.setFecha(reservaOutputDto.getFecha());
       reserva.setHora(reservaOutputDto.getHora());
       reserva.setCiudadDestino(reservaOutputDto.getCiudadDestino());
       reserva.setCorreoElectronico(reservaOutputDto.getCorreoElectronico());
       reserva.setMensaje(reservaOutputDto.getMensaje());
       reserva.setTelefono(reservaOutputDto.getTelefono());
        reservaInputDtoKafkaTemplate()
                .send("reservas-topic", reserva);

    }
}
