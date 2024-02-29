package com.bosonit.crudrelaciones.application;

import com.bosonit.crudrelaciones.controller.dto.AsignaturaInputDto;
import com.bosonit.crudrelaciones.controller.dto.AsignaturaOutputDto;

import java.util.List;

public interface AsignaturaServicio {
    AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInputDto);
    AsignaturaOutputDto updateAsignatura(AsignaturaInputDto asignaturaInputDto);
    void deleteAsignaturaById (int id);
    List<AsignaturaOutputDto> getAsignaturasPorEstudiante(int estudiante_id);
    AsignaturaOutputDto addEstudiantesToAsignatura(int asignatura_id, int estudiante_id);
    AsignaturaOutputDto removeEstudiantesToAsignatura(int asignatura_id, int estudiante_id);
}