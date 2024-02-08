package com.bosonit.block7crud.services;

import com.bosonit.block7crud.entity.Persona;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonaService {
    Persona addPersona(Persona persona);
    List<Persona> getAllPersonas();
    Persona updatePersona(Long id, Persona personaDetails) throws ClassNotFoundException;
    void deletePersona(Long id) throws ClassNotFoundException;
    Persona getPersonaById(Long id) throws ClassNotFoundException;
    List<Persona> getPersonasByNombre(String nombre);
}
