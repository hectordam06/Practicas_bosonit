package com.bosonit.block6personcontrollers.controllers;

import com.bosonit.block6personcontrollers.models.Ciudad;
import com.bosonit.block6personcontrollers.models.Persona;
import com.bosonit.block6personcontrollers.services.CiudadService;
import com.bosonit.block6personcontrollers.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controlador1 {
    @Autowired
    private PersonaService personaService;
    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/controlador1/addPersona")
    public Persona addPersona(@RequestHeader String nombre, @RequestHeader String poblacion, @RequestHeader int edad) {
        return personaService.crearPersona(nombre, poblacion, edad);
}
    @PostMapping("/controlador1/addCiudad")
    public Ciudad agregarCiudad(@RequestBody Ciudad ciudad) {
        ciudadService.anadirCiudad(ciudad);
        return ciudad;
    }
}
