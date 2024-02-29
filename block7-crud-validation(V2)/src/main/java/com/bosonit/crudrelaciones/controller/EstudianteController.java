package com.bosonit.crudrelaciones.controller;

import com.bosonit.crudrelaciones.application.AsignaturaServicio;
import com.bosonit.crudrelaciones.application.EstudianteServicio;
import com.bosonit.crudrelaciones.controller.dto.AsignaturaOutputDto;
import com.bosonit.crudrelaciones.controller.dto.EstudianteInputDto;
import com.bosonit.crudrelaciones.controller.dto.EstudianteSimpleOutputDto;
import com.bosonit.crudrelaciones.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    @Autowired
    EstudianteServicio estudianteServicio;
    @Autowired
    AsignaturaServicio asignaturaServicio;

    @PostMapping
    public ResponseEntity<EstudianteSimpleOutputDto> addEstudianteToPersona(
            @RequestBody EstudianteInputDto estudianteInputDto){
        return ResponseEntity.ok( estudianteServicio.addEstudianteToPersona(estudianteInputDto));
    }

    @GetMapping("/{id_estudiante}")
    public ResponseEntity<?> estudianteOutputDto(
            @RequestParam(value = "outputType", defaultValue = "simple") String outputType,
            @PathVariable int id_estudiante){
        return ResponseEntity.ok(estudianteServicio.getEstudianteToSimpleFullPersona(id_estudiante, outputType));
    }

    @GetMapping
    public List<EstudianteSimpleOutputDto> getAllStudents(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        List<EstudianteSimpleOutputDto> students = (List<EstudianteSimpleOutputDto>) estudianteServicio.getAllStudents(pageNumber, pageSize);

        if (students.isEmpty()) {
            throw new EntityNotFoundException("No hay estudiantes registrados.");
        }
        return students;
    }


    @GetMapping("{id_estudiante}/asignaturas")
    public ResponseEntity<List<AsignaturaOutputDto>> getAsignaturasPorEstudiante(@PathVariable int id_estudiante) {
        List<AsignaturaOutputDto> asignaturas = asignaturaServicio.getAsignaturasPorEstudiante(id_estudiante);
        if (asignaturas.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron asignaturas para el estudiante con ID: " + id_estudiante);
        }
        return ResponseEntity.ok(asignaturas);
    }

    @DeleteMapping("/{id_estudiante}")
    public void deletePersonaById(@PathVariable int id_estudiante) {
        estudianteServicio.deleteEstudianteById(id_estudiante);
    }

}