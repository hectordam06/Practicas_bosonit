package com.bosonit.block6personcontrollers.services;

import com.bosonit.block6personcontrollers.models.Persona;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class PersonaService {
    Persona persona= new Persona();
    public Persona crearPersona(String nombre, String poblacion, int edad) {
        persona.setNombre(nombre);
        persona.setPoblacion(poblacion);
        persona.setEdad(edad);
        return persona;
    }


}
