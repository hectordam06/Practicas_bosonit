package com.bosonit.block7crudvalidation.services;

import com.bosonit.block7crudvalidation.controlles.dto.profesor.ProfesorInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.profesor.ProfesorOutputDto;

import java.util.List;

public interface ProfesorService {
    ProfesorOutputDto addProfesor(ProfesorInputDto profesorInputDto);

    ProfesorOutputDto getProfesor(String id);

    List<ProfesorOutputDto> getAllProfesores();

    ProfesorOutputDto updateProfesor(String id, ProfesorInputDto profesorInputDto);

    void deleteProfesor(String id);
}
