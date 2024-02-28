package com.bosonit.block7crudvalidation.controlles.dto.profesor;

import com.bosonit.block7crudvalidation.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @PostMapping
    public ProfesorOutputDto addProfesor(@RequestBody ProfesorInputDto profesorInputDto) {
        return profesorService.addProfesor(profesorInputDto);
    }

    @GetMapping("/{id}")
    public ProfesorOutputDto getProfesor(@PathVariable String id) {
        return profesorService.getProfesor(id);
    }

    @GetMapping
    public List<ProfesorOutputDto> getAllProfesores() {
        return profesorService.getAllProfesores();
    }

    @PutMapping("/{id}")
    public ProfesorOutputDto updateProfesor(@PathVariable String id, @RequestBody ProfesorInputDto profesorInputDto) {
        return profesorService.updateProfesor(id, profesorInputDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProfesor(@PathVariable String id) {
        profesorService.deleteProfesor(id);
    }
}