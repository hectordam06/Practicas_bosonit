package com.bosonit.crudrelaciones.application.feign;

import com.bosonit.crudrelaciones.controller.dto.ProfesorOutputDto;
import com.bosonit.crudrelaciones.domain.Profesor;
import com.bosonit.crudrelaciones.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorFeignClient implements MyFeign {

    @Autowired
    ProfesorRepository profesorRepository;
    @Override
    public ProfesorOutputDto getProfesorById(int id) {
        Profesor profesor = profesorRepository.findById(id).orElseThrow();
        return profesor.PersonaToProfesor_ProfesorOutputDto();
    }
}