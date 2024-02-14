package com.bosonit.block7crudvalidation.services;

import com.bosonit.block7crudvalidation.controlles.dto.PersonaInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.PersonaOutputDto;
import com.bosonit.block7crudvalidation.models.Persona;

import java.util.List;

public interface PersonaService {
    PersonaOutputDto getById(int id);
    PersonaOutputDto getByUsuario(String usuario);
    List<PersonaOutputDto> getAll();
    public void addPersona(PersonaInputDto persona) throws Exception;
   public void updatePersona(int id, PersonaInputDto persona) throws Exception;
   public void deletePersona(int id);
}
