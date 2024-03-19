package com.bosonit.block13mongodb.controller;

import com.bosonit.block13mongodb.model.Persona;
import com.bosonit.block13mongodb.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    public PersonaService personaService;

    @PostMapping
    public Persona agregarPersona(@RequestBody Persona persona) {
        return personaService.agregarPersona(persona);
    }

    @GetMapping
    public List<Persona> obtenerPersonas(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10") int tamaño) {
        return personaService.obtenerPersonas(pagina, tamaño);
    }

    @GetMapping("/{id}")
    public Persona obtenerPersonaPorId(@PathVariable String id) {
        return personaService.obtenerPersonaPorId(id);
    }

    @PutMapping("/{id}")
    public Persona actualizarPersona(@PathVariable String id, @RequestBody Persona persona) {
        return personaService.actualizarPersona(id, persona);
    }

    @DeleteMapping("/{id}")
    public void eliminarPersona(@PathVariable String id) {
        personaService.eliminarPersona(id);
    }
}
