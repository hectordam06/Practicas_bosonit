package com.bosonit.block6personcontrollers.config;

import com.bosonit.block6personcontrollers.models.Persona;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonaConfig {
    @Bean
    @Qualifier("bean1")
    public Persona bean1() {
        Persona persona = new Persona();
        persona.setNombre("bean1");
        return persona;
    }

    @Bean
    @Qualifier("bean2")
    public Persona bean2() {
        Persona persona = new Persona();
        persona.setNombre("bean2");
        return persona;
    }

    @Bean
    @Qualifier("bean3")
    public Persona bean3() {
        Persona persona = new Persona();
        persona.setNombre("bean3");
        return persona;
    }
}
