package com.bosonit.block7crudvalidation.controlles.dto.asignatura;

import com.bosonit.block7crudvalidation.services.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/asignaturas")
public class AsignaturaController {
    @Autowired
    private AsignaturaService asignaturaService;

    @PostMapping
    public AsignaturaOutputDto addAsignatura(@RequestBody AsignaturaInputDto asignaturaInputDto) {
        return asignaturaService.addAsignatura(asignaturaInputDto);
    }

    @GetMapping("{id}")
    public AsignaturaOutputDto getAsignatura(@PathVariable String id) {
        return asignaturaService.getAsignatura(id);
    }

    @GetMapping
    public List<AsignaturaOutputDto> getAllAsignaturas() {
        return asignaturaService.getAllAsignaturas();
    }

    @PutMapping("/{id}")
    public AsignaturaOutputDto updateAsignatura(@PathVariable String id, @RequestBody AsignaturaInputDto asignaturaInputDto) {
        return asignaturaService.updateAsignatura(id, asignaturaInputDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAsignatura(@PathVariable String id) {
        asignaturaService.deleteAsignatura(id);
    }
}
