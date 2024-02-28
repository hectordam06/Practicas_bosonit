package com.bosonit.block7crudvalidation.services;

import com.bosonit.block7crudvalidation.controlles.dto.persona.PersonaInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.persona.PersonaOutputDto;
import com.bosonit.block7crudvalidation.controlles.dto.persona.PersonaWithRoleDto;
import com.bosonit.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.bosonit.block7crudvalidation.models.Persona;
import com.bosonit.block7crudvalidation.models.Profesor;
import com.bosonit.block7crudvalidation.models.Student;
import com.bosonit.block7crudvalidation.repositories.PersonaRepository;
import com.bosonit.block7crudvalidation.repositories.ProfesorRepository;
import com.bosonit.block7crudvalidation.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    PersonaRepository personaRepositorie;
    @Override
    public PersonaOutputDto getById(String id) {
        return personaRepositorie.findById((id)).orElseThrow(EntityNotFoundException::new).personatoPersonaOutputDto();
    }

    public PersonaOutputDto getByUsuario(String usuario) {
        Persona persona = personaRepositorie.findByUsuario(usuario);
        if (persona == null) {
            throw new EntityNotFoundException();
        }
        return persona.personatoPersonaOutputDto();
    }

    @Override
    public List<PersonaOutputDto> getAll() {
        return personaRepositorie.findAll().stream().map(Persona::personatoPersonaOutputDto).toList();
    }

    @Override
    public void addPersona(PersonaInputDto persona) throws UnprocessableEntityException {
        try {
            PersonaInputDto personaValidada = validarPersona(persona);
            personaRepositorie.save(new Persona(personaValidada));

        } catch (Exception e) {
            throw new UnprocessableEntityException(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage());
        }
    }

    @Override
    public void updatePersona(String id, PersonaInputDto persona) throws Exception  {
try {
    validarPersona(persona);
    Persona personaExistente = personaRepositorie.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No se encontró la persona con el ID: " + id));

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

} catch (UnprocessableEntityException e) {
    throw new UnprocessableEntityException(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage());
}

    }




    @Override
    public void deletePersona(String id) throws EntityNotFoundException {
            personaRepositorie.deleteById(id);

    }



    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private StudentRepository estudianteRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public PersonaWithRoleDto getByIdWithRole(String id) {
        Persona persona = personaRepository.findById(id).orElse(null);
        if (persona != null) {
            Student estudiante = (Student) estudianteRepository.findByPersonaId(persona.getId());
            Profesor profesor = (Profesor) profesorRepository.findByPersonaId(persona.getId());
            return new PersonaWithRoleDto(persona.getIdPersona(), persona.getUsuario(), persona.getName(), persona.getSurname(), persona.getCompanyEmail(), persona.getPersonalEmail(), persona.getCity(), persona.isActive(), persona.getCreatedDate(), persona.getImageUrl(), persona.getTerminationDate(), estudiante != null || profesor != null);
        } else {
            return null;
        }
    }

    @Override
    public PersonaWithRoleDto getByUsuarioWithRole(String usuario) {
        Persona persona = personaRepository.findByUsuario(usuario);
        if (persona != null) {
            Student estudiante = (Student) estudianteRepository.findByPersonaId(persona.getId());
            Profesor profesor = (Profesor) profesorRepository.findByPersonaId(persona.getId());
            return new PersonaWithRoleDto(persona.getIdPersona(), persona.getUsuario(), persona.getName(), persona.getSurname(), persona.getCompanyEmail(), persona.getPersonalEmail(), persona.getCity(), persona.isActive(), persona.getCreatedDate(), persona.getImageUrl(), persona.getTerminationDate(), estudiante != null || profesor != null);
        } else {
            return null;
        }
    }

    @Override
    public List<PersonaWithRoleDto> getAllWithRole() {
        List<PersonaWithRoleDto> personasConRoles = new ArrayList<>();
        List<Persona> personas = personaRepository.findAll();
        for (Persona persona : personas) {
            Student estudiante = (Student) estudianteRepository.findByPersonaId(persona.getId());
            Profesor profesor = (Profesor) profesorRepository.findByPersonaId(persona.getId());
            personasConRoles.add(new PersonaWithRoleDto(persona.getIdPersona(), persona.getUsuario(), persona.getName(), persona.getSurname(), persona.getCompanyEmail(), persona.getPersonalEmail(), persona.getCity(), persona.isActive(), persona.getCreatedDate(), persona.getImageUrl(), persona.getTerminationDate(), estudiante != null || profesor != null));
        }
        return personasConRoles;
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
