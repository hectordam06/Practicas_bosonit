package com.bosonit.crudrelaciones.controller;

import com.bosonit.crudrelaciones.application.PersonaServicioImpl;
import com.bosonit.crudrelaciones.controller.dto.PersonaInputDto;
import com.bosonit.crudrelaciones.controller.dto.PersonaOutputDto;
import com.bosonit.crudrelaciones.controller.dto.PersonaRolOutputDto;
import com.bosonit.crudrelaciones.exceptions.EntityNotFoundException;
import com.bosonit.crudrelaciones.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class Controller {

    @Autowired
    PersonaServicioImpl personaServicio;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto persona){
        validarPersona(persona);
        return ResponseEntity.status(200)
                .body(personaServicio.addPersona(persona));
    }

    @GetMapping("/{id_persona}")
    public ResponseEntity<PersonaRolOutputDto> getPersonaById(
            @PathVariable int id_persona,
            @RequestParam(defaultValue = "false") boolean rol)
    {
        return ResponseEntity.ok().body(personaServicio.getPersonaById(id_persona, rol));
    }
    @GetMapping("/nombre/{name}")
    public List<PersonaRolOutputDto> getAllPersonasByNameLike(
            @PathVariable String nombre,
            @RequestParam(defaultValue = "false") boolean rol) {
        List<PersonaRolOutputDto> personas = (List<PersonaRolOutputDto>) personaServicio.getPersonasByName(nombre, rol);

        if (personas.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron personas con el nombre: " + nombre);
        }
        return personas;
    }

    @GetMapping
    public List<PersonaRolOutputDto> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize,
            @RequestParam(defaultValue = "false") boolean rol) {
        List<PersonaRolOutputDto> personas = (List<PersonaRolOutputDto>) personaServicio.getAllPersonas(pageNumber, pageSize, rol);

        if (personas.isEmpty()) {
            throw new EntityNotFoundException("No hay personas registradas.");
        }
        return personas;
    }


    @DeleteMapping("/{id_persona}")
    public ResponseEntity<String> deletePersonaById(@PathVariable int id_persona) {
        personaServicio.deletePersonaById(id_persona);
        return ResponseEntity.ok().body("Persona con id: " + id_persona + " borrada");
    }

    @PutMapping("/{id_persona}")
    public ResponseEntity<PersonaOutputDto> updatePersona(
            @PathVariable int id_persona,
            @RequestBody(required = false) PersonaInputDto persona) {

        PersonaRolOutputDto existingPersona = personaServicio.getPersonaById(id_persona, false);

        if (existingPersona == null){
            throw new EntityNotFoundException("No se encontró ninguna persona con el Id: " +id_persona);
        }

        PersonaInputDto existingPersonaInput = new PersonaInputDto();
        existingPersonaInput.setId_persona(existingPersona.getId_persona());
        existingPersonaInput.setName(existingPersona.getName());
        existingPersonaInput.setUsuario(existingPersona.getUsuario());
        existingPersonaInput.setSurname(existingPersona.getSurname());
        existingPersonaInput.setCity(existingPersona.getCity());
        existingPersonaInput.setPassword(existingPersona.getPassword());
        existingPersonaInput.setCreated_date(existingPersona.getCreated_date());
        existingPersonaInput.setCompany_email(existingPersona.getCompany_email());
        existingPersonaInput.setPersonal_email(existingPersona.getPersonal_email());
        existingPersonaInput.setActive(existingPersona.getActive());
        existingPersonaInput.setImagen_url(existingPersona.getImagen_url());
        existingPersonaInput.setTermination_date(existingPersona.getTermination_date());


        if (persona.getName() != null) {
            existingPersonaInput.setName(persona.getName());
        }
        if (persona.getUsuario() != null){
            existingPersonaInput.setUsuario(persona.getUsuario());
        }
        if (persona.getCity() != null) {
            existingPersonaInput.setCity(persona.getCity());
        }
        PersonaOutputDto updatePersona = personaServicio
                .updatePersona(existingPersonaInput);
        if(updatePersona != null){
            return ResponseEntity.ok().body(updatePersona);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    private void validarPersona(PersonaInputDto persona){
        if (persona.getUsuario().length() < 6 || persona.getUsuario().length() > 10) {
            throw new UnprocessableEntityException("El nombre de usuario debe tener entre 6 y 10 caracteres");
        }
        if (persona.getPassword() == null) {
            throw new UnprocessableEntityException("La contraseña no puede estar vacía");
        }
        if (persona.getCompany_email() == null) {
            throw new UnprocessableEntityException("Escriba el email de su compañía");
        }
        if (persona.getCity() == null) {
            throw new UnprocessableEntityException("El campo ciudad no puede ser nulo");
        }
        if (persona.getCreated_date() == null) {
            throw new UnprocessableEntityException("El campo de fecha de creación no puede ser nulo");
        }
    }
}