package com.bosonit.block7crudvalidation.services;

import com.bosonit.block7crudvalidation.controlles.dto.profesor.ProfesorInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.profesor.ProfesorOutputDto;
import com.bosonit.block7crudvalidation.exceptions.EntityNotFoundException;
import com.bosonit.block7crudvalidation.models.Asignatura;
import com.bosonit.block7crudvalidation.models.Persona;
import com.bosonit.block7crudvalidation.models.Profesor;
import com.bosonit.block7crudvalidation.repositories.PersonaRepository;
import com.bosonit.block7crudvalidation.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServiceImpl  implements  ProfesorService{
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Override
    public ProfesorOutputDto addProfesor(ProfesorInputDto profesorInputDto) {
        Persona persona= personaRepository.findById(profesorInputDto.getId_persona()).orElseThrow() ;

        Profesor profesor= new Profesor(profesorInputDto);
        profesor.setPersona(persona);
        return profesorRepository.save(profesor).profesortoProfesorOutputDto();
    }

    @Override
    public ProfesorOutputDto getProfesor(String id) {

        return profesorRepository.findById(id).get().profesortoProfesorOutputDto();
    }

    @Override
    public List<ProfesorOutputDto> getAllProfesores() {

        return profesorRepository.findAll().stream().map(Profesor::profesortoProfesorOutputDto).toList();
    }

    @Override
    public ProfesorOutputDto updateProfesor(String id, ProfesorInputDto profesorInputDto) {
        Profesor profesor = profesorRepository.findById(id).orElse(null);
        if (profesor != null) {

            profesor.setComents(profesorInputDto.getComents());
            profesor.setBranch(profesorInputDto.getBranch());
          //  profesor.setEstudiantes(profesorInputDto.getEstudiantes());
            return profesorRepository.save(profesor).profesortoProfesorOutputDto();
        } else {
            // Manejar las excepciones
            return null;
        }
    }

    @Override
    public void deleteProfesor(String id) {
        profesorRepository.deleteById(id);
    }
}
