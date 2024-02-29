package com.bosonit.crudrelaciones.controller;

import com.bosonit.crudrelaciones.application.AsignaturaServicio;
import com.bosonit.crudrelaciones.controller.dto.AsignaturaInputDto;
import com.bosonit.crudrelaciones.controller.dto.AsignaturaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaController {
    @Autowired
    AsignaturaServicio asignaturaServicio;

    @PostMapping
    public ResponseEntity<AsignaturaOutputDto> addAsignatura(@RequestBody AsignaturaInputDto asignaturaInputDto)
    {
        AsignaturaOutputDto asignaturaOutputDto = asignaturaServicio.addAsignatura(asignaturaInputDto);
        return ResponseEntity.ok(asignaturaOutputDto);
    }

    @GetMapping("/estudiante/{estudiante_id}/asignaturas")
    public ResponseEntity<List<AsignaturaOutputDto>> getAsignaturasPorEstudiante(@PathVariable int estudiante_id) {
        List<AsignaturaOutputDto> asignaturas = asignaturaServicio.getAsignaturasPorEstudiante(estudiante_id);
        return ResponseEntity.ok(asignaturas);
    }


    @PostMapping("/{asignaturaId}/addEstudiantes")
    public ResponseEntity<AsignaturaOutputDto> addEstudiantesToAsignatura(@PathVariable int asignaturaId, @RequestBody List<Integer> estudiantesIds)
    {
        AsignaturaOutputDto asignaturaOutputDto = null;
        for (Integer estudianteId : estudiantesIds) {
            asignaturaOutputDto = asignaturaServicio.addEstudiantesToAsignatura(asignaturaId, estudianteId);
        }
        return ResponseEntity.ok(asignaturaOutputDto);
    }
    @PostMapping("/{asignaturaId}/removeEstudiantes")
    public ResponseEntity<AsignaturaOutputDto> removeEstudiantesToAsignatura(@PathVariable int asignaturaId, @RequestBody List<Integer> estudiantesIds) {
        AsignaturaOutputDto asignaturaOutputDto = null;
        for (Integer estudianteId : estudiantesIds) {
            asignaturaOutputDto = asignaturaServicio.removeEstudiantesToAsignatura(asignaturaId, estudianteId);
        }
        return ResponseEntity.ok(asignaturaOutputDto);
    }
    @DeleteMapping("/{id_asignatura}")
    public void deleteAsignaturaById(@PathVariable int id_asignatura)
    {
        asignaturaServicio.deleteAsignaturaById(id_asignatura);
    }
}