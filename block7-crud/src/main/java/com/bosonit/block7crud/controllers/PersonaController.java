package com.bosonit.block7crud.controllers;

import com.bosonit.block7crud.entity.Persona;
import com.bosonit.block7crud.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<String> addPersona(@RequestBody Persona persona) {

        personaService.addPersona(persona);
        return ResponseEntity.status(200).body("Persona añadida correctamente.");
    }

    @PutMapping("/{id}")
    public Persona updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails) throws ClassNotFoundException {
        return personaService.updatePersona(id, personaDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePersona(@PathVariable Long id) throws ClassNotFoundException {
        personaService.deletePersona(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonaById(@PathVariable Long id) {
        try {
            Persona persona = personaService.getPersonaById(id);
            return ResponseEntity.ok(persona);
        } catch (ClassNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada.");
        }
    }

    @GetMapping("/nombre/{nombre}")
    public List<Persona> getPersonasByNombre(@PathVariable String nombre) {
        return personaService.getPersonasByNombre(nombre);
    }

    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.getAllPersonas();
    }
}
