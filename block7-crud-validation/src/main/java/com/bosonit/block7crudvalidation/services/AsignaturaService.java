package com.bosonit.block7crudvalidation.services;

import com.bosonit.block7crudvalidation.controlles.dto.asignatura.AsignaturaInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.asignatura.AsignaturaOutputDto;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInputDto);

    AsignaturaOutputDto getAsignatura(String id);

    List<AsignaturaOutputDto> getAllAsignaturas();

    AsignaturaOutputDto updateAsignatura(String id, AsignaturaInputDto asignaturaInputDto);

    void deleteAsignatura(String id);

    List<AsignaturaOutputDto> getByIdEstudiante(String idEstuadinte);

}
