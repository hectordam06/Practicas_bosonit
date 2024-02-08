package com.bosonit.block7crud.services;

import com.bosonit.block7crud.entity.Persona;
import com.bosonit.block7crud.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements   PersonaService{
    @Autowired
    PersonaRepository personaRepository;
    @Override
    public Persona addPersona(Persona persona) {

        return personaRepository.save(persona);
    }

    @Override
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public Persona updatePersona(Long id, Persona personaDetails) throws ClassNotFoundException {

        Optional<Persona> optionalPersona = personaRepository.findById(id);
        if (optionalPersona.isEmpty()) {
            throw new ClassNotFoundException("Persona no encontrada.");
        }

        Persona persona = optionalPersona.get();

        if (personaDetails.getNombre() != null) {
            persona.setNombre(personaDetails.getNombre());
        }
        if (personaDetails.getPoblacion() != null) {
            persona.setPoblacion(personaDetails.getPoblacion());
        }

        return personaRepository.save(persona);
    }

    @Override
    public void deletePersona(Long id) throws ClassNotFoundException {
        if (!personaRepository.existsById(id)) {
            throw new ClassNotFoundException("Persona no encontrada.");
        }
        personaRepository.deleteById(id);

    }

    @Override
    public Persona getPersonaById(Long id) throws ClassNotFoundException {
        Optional<Persona> optionalPersona = personaRepository.findById(id);
        return optionalPersona.orElseThrow(() -> new ClassNotFoundException("Persona no encontrada."));
    }

    @Override
    public List<Persona> getPersonasByNombre(String nombre) {
        return personaRepository.findByNombre(nombre);
    }
}
