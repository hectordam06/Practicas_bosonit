package com.bosonit.block6personcontrollers.services;

import com.bosonit.block6personcontrollers.models.Ciudad;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadService {
    private List<Ciudad> ciudades = new ArrayList<>();

    public void anadirCiudad(Ciudad ciudad){
        ciudades.add(ciudad);
    }
    public List<Ciudad> obtenerCiudades() {
        return ciudades;
    }
}
