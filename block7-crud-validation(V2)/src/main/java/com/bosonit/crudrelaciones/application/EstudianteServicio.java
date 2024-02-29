package com.bosonit.crudrelaciones.application;

import com.bosonit.crudrelaciones.controller.dto.EstudianteInputDto;
import com.bosonit.crudrelaciones.controller.dto.EstudianteSimpleOutputDto;

public interface EstudianteServicio {

    EstudianteSimpleOutputDto addEstudianteToPersona (EstudianteInputDto estudianteInputDto);
    EstudianteSimpleOutputDto getEstudianteToSimpleFullPersona (int id_estudiante, String outputType);
    Iterable<EstudianteSimpleOutputDto> getAllStudents(int pageNumber, int pageSize);
    EstudianteSimpleOutputDto updateEstudiante(EstudianteInputDto estudianteInputDto);
    void deleteEstudianteById (int id);
}
