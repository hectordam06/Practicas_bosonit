package com.bosonit.crudrelaciones.application;

import com.bosonit.crudrelaciones.controller.dto.ProfesorInputDto;
import com.bosonit.crudrelaciones.controller.dto.ProfesorOutputDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface ProfesorServicio {
    @PostMapping
    ProfesorOutputDto addProfesorToPersona (ProfesorInputDto profesorInputDto);
    @PutMapping
    ProfesorOutputDto updateProfesor(ProfesorInputDto profesorInputDto);
    @GetMapping
    Iterable<ProfesorOutputDto> getAllProfesores(int pageNumber, int pageSize);
    @DeleteMapping
    void deleteProfesorById (int id);
}