package com.bosonit.block7crudvalidation.services;

import com.bosonit.block7crudvalidation.controlles.dto.PersonaInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.PersonaOutputDto;
import com.bosonit.block7crudvalidation.models.Persona;
import com.bosonit.block7crudvalidation.repositories.PersonaRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    PersonaRepositorie personaRepositorie;
    @Override
    public PersonaOutputDto getById(int id) {
        return personaRepositorie.findById(id).orElseThrow().personatoPersonaOutputDto();
    }

    @Override
    public PersonaOutputDto getByUsuario(String usuario) {
        return personaRepositorie.findByUsuario(usuario).personatoPersonaOutputDto();
    }

    @Override
    public List<PersonaOutputDto> getAll() {
        return personaRepositorie.findAll().stream().map(Persona::personatoPersonaOutputDto).toList();
    }

    @Override
    public void addPersona(PersonaInputDto persona) throws Exception {
        PersonaInputDto personaValidada = validarPersona(persona);
        personaRepositorie.save(new Persona(personaValidada));
    }

    @Override
    public void updatePersona(int id, PersonaInputDto persona) throws Exception {

        validarPersona(persona);
        Persona personaExistente = personaRepositorie.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la persona con el ID: " + id));


        personaExistente.setUsuario(persona.getUsuario());
        personaExistente.setName(persona.getName());
        personaExistente.setSurname(persona.getSurname());
        personaExistente.setCompanyEmail(persona.getCompanyEmail());
        personaExistente.setPersonalEmail(persona.getPersonalEmail());
        personaExistente.setCity(persona.getCity());
        personaExistente.setActive(persona.isActive());
        personaExistente.setCreatedDate(persona.getCreatedDate());
        personaExistente.setImageUrl(persona.getImageUrl());
        personaExistente.setTerminationDate(persona.getTerminationDate());

        personaRepositorie.save(personaExistente);
    }

    @Override
    public void deletePersona(int id) {
        personaRepositorie.deleteById(id);
    }

    PersonaInputDto validarPersona(PersonaInputDto persona)throws Exception{

        if (persona.getUsuario() == null) {
            throw new Exception("El usuario no puede ser nulo");
        }
        if (persona.getUsuario().length() < 6 || persona.getUsuario().length() > 10) {
            throw new Exception("La longitud del usuario debe estar entre 6 y 10 caracteres");
        }

        if (persona.getPassword() == null) {
            throw new Exception("La contraseña no puede ser nula");
        }

        if (persona.getName() == null || persona.getName().isEmpty()) {
            throw new Exception("El nombre no puede ser nulo o vacío");
        }

        if (persona.getCompanyEmail() == null) {
            throw new Exception("El correo electrónico de la empresa no puede ser nulo");
        }

        if (persona.getPersonalEmail() == null) {
            throw new Exception("El correo electrónico personal no puede ser nulo");
        }


        if (persona.getCity() == null || persona.getCity().isEmpty()) {
            throw new Exception("La ciudad no puede ser nula o vacía");
        }


        if (persona.getCreatedDate() == null) {
            throw new Exception("La fecha de creación no puede ser nula");
        }
        return persona;
    }
}
