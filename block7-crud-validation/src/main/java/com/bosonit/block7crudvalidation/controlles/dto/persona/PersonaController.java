package com.bosonit.block7crudvalidation.controlles.dto.persona;

import com.bosonit.block7crudvalidation.controlles.dto.feing.ProfesorFeignClient;
import com.bosonit.block7crudvalidation.controlles.dto.profesor.ProfesorOutputDto;
import com.bosonit.block7crudvalidation.repositories.ProfesorRepository;
import com.bosonit.block7crudvalidation.repositories.StudentRepository;
import com.bosonit.block7crudvalidation.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {


    @Autowired
    private PersonaService personaService1;

    @Autowired
    private StudentRepository estudianteRepository;

    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private ProfesorFeignClient profesorClient;

    @GetMapping("/persona/profesor/{id}")
    public ProfesorOutputDto getProfesor(@PathVariable int id) {
        return profesorClient.getProfesor(String.valueOf(id));
    }

    @GetMapping("/idRol/{id}")
    public PersonaWithRoleDto getByIdWithRole(@PathVariable String id) {
        return personaService1.getByIdWithRole(id);
    }

    @GetMapping("/usuarioRol/{usuario}")
    public PersonaWithRoleDto getByUsuarioWithRole(@PathVariable String usuario) {
        return personaService1.getByUsuarioWithRole(usuario);
    }

    @GetMapping("/with-role")
    public List<PersonaWithRoleDto> getAllWithRolefull() {
        return personaService1.getAllWithRole();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getByIdSimple(@PathVariable String id, @RequestParam(defaultValue = "simple") String outputType) {
        if (outputType.equals("full")) {
            PersonaWithRoleDto persona = personaService1.getByIdWithRole((id));
            return ResponseEntity.ok(persona);
        } else {
            PersonaOutputDto persona = personaService1.getById((id));
            return ResponseEntity.ok(persona);
        }
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<?> getByUsuario(@PathVariable String usuario, @RequestParam(defaultValue = "simple") String outputType) {
        if (outputType.equals("full")) {
            PersonaWithRoleDto persona = personaService1.getByUsuarioWithRole(usuario);
            return ResponseEntity.ok(persona);
        } else {
            PersonaOutputDto persona = personaService1.getByUsuario(usuario);
            return ResponseEntity.ok(persona);
        }
    }

    @GetMapping("/with-WithowtRole")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "simple") String outputType) {
        if (outputType.equals("full")) {
            List<PersonaWithRoleDto> personas = personaService1.getAllWithRole();
            return ResponseEntity.ok(personas);
        } else {
            List<PersonaOutputDto> personas = personaService1.getAll();
            return ResponseEntity.ok(personas);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getById(@PathVariable String id) {
        PersonaOutputDto persona = personaService1.getById(id);
        return ResponseEntity.ok(persona);
    }

    @GetMapping("{usuario}")
    public ResponseEntity<PersonaOutputDto> getByUsuarioSimple(@PathVariable String usuario) {
        PersonaOutputDto persona = personaService1.getByUsuario(usuario);
        return ResponseEntity.ok(persona);
    }

    @GetMapping
    //No devolver una lista sino un objeto 
    public ResponseEntity<List<PersonaOutputDto>> getAll() {
        List<PersonaOutputDto> personas = personaService1.getAll();
        return ResponseEntity.ok(personas);
    }

    @PostMapping
    //no debolver sting sino objeto
    public ResponseEntity<String> addPersona(@RequestBody PersonaInputDto persona) {
        try {
            personaService1.addPersona(persona);
            return ResponseEntity.ok("Persona añadida correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al añadir persona: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePersona(@PathVariable String id, @RequestBody PersonaInputDto persona) {
        try {
            personaService1.updatePersona(id, persona);
            return ResponseEntity.ok("Persona actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar persona: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersona(@PathVariable String id) {
        try {
            personaService1.deletePersona(id);
            return ResponseEntity.ok("Persona eliminada correctamente");
        }
           catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Persona no encontrada. " + e.getMessage());
        }


    }
}
