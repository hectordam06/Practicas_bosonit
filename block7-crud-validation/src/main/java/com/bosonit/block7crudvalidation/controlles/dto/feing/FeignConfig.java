package com.bosonit.block7crudvalidation.controlles.dto.feing;

import com.bosonit.block7crudvalidation.controlles.dto.persona.PersonaInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.profesor.ProfesorOutputDto;
import com.bosonit.block7crudvalidation.models.Persona;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class FeignConfig {

    @Bean
    public ProfesorFeignClient profesorFeignClient() {
        return new ProfesorFeignClient() {
            @Override
            public ProfesorOutputDto getProfesor(String id) {
                ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto();
                profesorOutputDto.setId(id);
                profesorOutputDto.setPersona(new Persona(new PersonaInputDto())); // Aquí deberías asignar una persona adecuada
                profesorOutputDto.setComents("Comentarios del profesor");
                profesorOutputDto.setBranch("Rama del profesor");
                profesorOutputDto.setEstudiantes(new ArrayList<>());
                return profesorOutputDto;
            }
        };
    }
}