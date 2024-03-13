package com.bosonit.crudrelaciones.controller;

import com.bosonit.crudrelaciones.application.PersonaServicio;
import com.bosonit.crudrelaciones.controller.dto.PersonaInputDto;
import com.bosonit.crudrelaciones.controller.dto.PersonaOutputDto;
import com.bosonit.crudrelaciones.controller.dto.PersonaRolOutputDto;
import com.bosonit.crudrelaciones.domain.Persona;
import com.bosonit.crudrelaciones.exceptions.EntityNotFoundException;
import com.bosonit.crudrelaciones.repository.PersonaRepository;
import com.bosonit.crudrelaciones.repository.PersonaRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "https://codepen.io")
public class PersonaController {

    @Autowired
    PersonaServicio personaServicio;
    @Autowired
    PersonaRepositoryCustom personaRepositoryCustom;



    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto personaInputDto)
    {
        return ResponseEntity.ok(personaServicio.addPersona(personaInputDto));
    }

    @GetMapping("/{id_persona}")
    public ResponseEntity<PersonaRolOutputDto> getPersonaById(@PathVariable int id_persona, @RequestParam(defaultValue = "false") boolean rol)
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
    public void deletePersonaById(@PathVariable int id_persona)
    {
        personaServicio.deletePersonaById(id_persona);
    }

    @PutMapping("/{id_persona}")
    public ResponseEntity<PersonaOutputDto> updatePersona(
            @PathVariable int id_persona,
            @RequestBody(required = false) PersonaInputDto personaInputDto)
    {
        return ResponseEntity.ok(personaServicio.updatePersona(personaInputDto));

    }


    @GetMapping("/search")
    public ResponseEntity<Page<PersonaOutputDto>> searchPersonas(
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate,
            @RequestParam(required = false, defaultValue = "user") String orderBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Persona> personas = personaRepositoryCustom.searchWithPagination(user, name, surname, startDate, endDate, orderBy, page, size);
        Page<PersonaOutputDto> personasOutputDto = personas.map(persona -> persona.personaToPersonaOutputDto());
        return ResponseEntity.ok(personasOutputDto);
    }
}

