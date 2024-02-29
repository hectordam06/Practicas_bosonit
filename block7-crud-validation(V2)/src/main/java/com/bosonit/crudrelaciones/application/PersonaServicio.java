package com.bosonit.crudrelaciones.application;

import com.bosonit.crudrelaciones.controller.dto.PersonaInputDto;
import com.bosonit.crudrelaciones.controller.dto.PersonaOutputDto;
import com.bosonit.crudrelaciones.controller.dto.PersonaRolOutputDto;

public interface PersonaServicio {
    PersonaOutputDto addPersona(PersonaInputDto personaInputDto);
    PersonaRolOutputDto getPersonaById(int id, boolean rol);
    Iterable<PersonaRolOutputDto> getPersonasByName(String name, boolean rol);
    Iterable<PersonaRolOutputDto> getAllPersonas (int pageNumber, int pageSize, boolean rol);
    PersonaOutputDto updatePersona(PersonaInputDto personaInputDto);
    void deletePersonaById (int id);
}