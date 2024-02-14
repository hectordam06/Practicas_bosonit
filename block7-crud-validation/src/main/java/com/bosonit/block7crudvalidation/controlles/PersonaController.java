package com.bosonit.block7crudvalidation.controlles;

import com.bosonit.block7crudvalidation.controlles.dto.PersonaInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.PersonaOutputDto;
import com.bosonit.block7crudvalidation.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getById(@PathVariable int id) {
        PersonaOutputDto persona = personaService.getById(id);
        return ResponseEntity.ok(persona);
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<PersonaOutputDto> getByUsuario(@PathVariable String usuario) {
        PersonaOutputDto persona = personaService.getByUsuario(usuario);
        return ResponseEntity.ok(persona);
    }

    @GetMapping
    public ResponseEntity<List<PersonaOutputDto>> getAll() {
        List<PersonaOutputDto> personas = personaService.getAll();
        return ResponseEntity.ok(personas);
    }

    @PostMapping
    public ResponseEntity<String> addPersona(@RequestBody PersonaInputDto persona) {
        try {
            personaService.addPersona(persona);
            return ResponseEntity.ok("Persona añadida correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al añadir persona: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePersona(@PathVariable int id, @RequestBody PersonaInputDto persona) {
        try {
            personaService.updatePersona(id, persona);
            return ResponseEntity.ok("Persona actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar persona: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersona(@PathVariable int id) {
        personaService.deletePersona(id);
        return ResponseEntity.ok("Persona eliminada correctamente");
    }
}
