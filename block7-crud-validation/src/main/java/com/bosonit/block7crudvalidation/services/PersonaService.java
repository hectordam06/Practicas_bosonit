package com.bosonit.block7crudvalidation.services;

import com.bosonit.block7crudvalidation.controlles.dto.persona.PersonaInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.persona.PersonaOutputDto;
import com.bosonit.block7crudvalidation.controlles.dto.persona.PersonaWithRoleDto;

import java.util.List;

public interface PersonaService {
    PersonaOutputDto getById(String id);
    PersonaOutputDto getByUsuario(String usuario);
    List<PersonaOutputDto> getAll();
    public void addPersona(PersonaInputDto persona) throws Exception;
   public void updatePersona(String id, PersonaInputDto persona) throws Exception;
   public void deletePersona(String id);
    PersonaWithRoleDto getByIdWithRole(String id);
    PersonaWithRoleDto getByUsuarioWithRole(String usuario);
    List<PersonaWithRoleDto> getAllWithRole();

}
