package com.bosonit.crudrelaciones.application;

import com.bosonit.crudrelaciones.application.feign.ProfesorFeignClient;
import com.bosonit.crudrelaciones.controller.dto.ProfesorInputDto;
import com.bosonit.crudrelaciones.controller.dto.ProfesorOutputDto;
import com.bosonit.crudrelaciones.domain.Persona;
import com.bosonit.crudrelaciones.domain.Profesor;
import com.bosonit.crudrelaciones.exceptions.EntityNotFoundException;
import com.bosonit.crudrelaciones.repository.EstudianteRepository;
import com.bosonit.crudrelaciones.repository.PersonaRepository;
import com.bosonit.crudrelaciones.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServicioImpl implements ProfesorServicio{
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    EstudianteRepository estudianteRepository;
    @Autowired
    private ProfesorFeignClient profesorFeignClient;
    @Override
    public ProfesorOutputDto addProfesorToPersona(ProfesorInputDto profesorInputDto) {
        if (estudianteRepository.existsById(profesorInputDto.getId_persona()))
        {
            throw new EntityNotFoundException("La persona ya es un estudiante");
        }
        Persona persona = personaRepository.findById(profesorInputDto.getId_persona()).orElseThrow();

        Profesor profesor = new Profesor(profesorInputDto);
        profesor.setPersona(persona);
        return profesorRepository.save(profesor).PersonaToProfesor_ProfesorOutputDto();
    }

    @Override
    public ProfesorOutputDto updateProfesor(ProfesorInputDto profesorInputDto) {
        profesorRepository.getReferenceById(profesorInputDto.getId_profesor());
        return profesorRepository.save(new Profesor(profesorInputDto)).PersonaToProfesor_ProfesorOutputDto();
    }

    @Override
    public Iterable<ProfesorOutputDto> getAllProfesores(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return profesorRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Profesor::PersonaToProfesor_ProfesorOutputDto).toList();
    }

    @Override
    public void deleteProfesorById(int id) {
        profesorRepository.getReferenceById(id);
        personaRepository.deleteById(id);
    }


}