package com.bosonit.block6personcontrollers.controllers;

import com.bosonit.block6personcontrollers.models.Ciudad;
import com.bosonit.block6personcontrollers.models.Persona;
import com.bosonit.block6personcontrollers.services.CiudadService;
import com.bosonit.block6personcontrollers.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controlador2 {
    @Autowired
    PersonaService personaService ;


    @GetMapping("/controlador2/getPersona")
    public Persona getPersona(){
        Persona persona = personaService.getPersona();
        persona.setEdad(persona.getEdad()*2);
        return persona;
    }

    @Autowired
    CiudadService ciudadService;

    @GetMapping("/controlador2/getCiudades")
    public List<Ciudad> getCiudades() {
        return ciudadService.obtenerCiudades();
    }
}
