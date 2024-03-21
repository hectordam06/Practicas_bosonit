package com.bosonit.block7crud;

import com.bosonit.block7crud.entity.Persona;
import com.bosonit.block7crud.repositories.PersonaRepository;
import com.bosonit.block7crud.services.PersonaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class PersonaServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PersonaServiceImpl personaService;

    @Test
    public void testAddPersona() {
        Persona personaToAdd = new Persona();
        when(personaRepository.save(personaToAdd)).thenReturn(personaToAdd);

        Persona addedPersona = personaService.addPersona(personaToAdd);

        assertNotNull(addedPersona);
        assertEquals(personaToAdd, addedPersona);
        verify(personaRepository, times(1)).save(personaToAdd);
    }

    @Test
    public void testGetAllPersonas() {
        List<Persona> personas = new ArrayList<>();
        when(personaRepository.findAll()).thenReturn(personas);

        List<Persona> result = personaService.getAllPersonas();

        assertNotNull(result);
        assertEquals(personas, result);
        verify(personaRepository, times(1)).findAll();
    }

    @Test
    public void testUpdatePersona_PersonaFound() throws ClassNotFoundException {
        Long id = 1L;
        Persona personaToUpdate = new Persona();
        personaToUpdate.setId(id);

        Optional<Persona> optionalPersona = Optional.of(personaToUpdate);
        when(personaRepository.findById(id)).thenReturn(optionalPersona);
        when(personaRepository.save(personaToUpdate)).thenReturn(personaToUpdate);

        Persona updatedPersona = personaService.updatePersona(id, personaToUpdate);

        assertNotNull(updatedPersona);
        assertEquals(personaToUpdate, updatedPersona);
        verify(personaRepository, times(1)).findById(id);
        verify(personaRepository, times(1)).save(personaToUpdate);
    }

    @Test
    public void testUpdatePersona_PersonaNotFound() {
        Long id = 1L;
        when(personaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ClassNotFoundException.class, () -> {
            personaService.updatePersona(id, new Persona());
        });

        verify(personaRepository, times(1)).findById(id);
        verify(personaRepository, never()).save(any());
    }

    @Test
    public void testDeletePersona_Success() throws ClassNotFoundException {
        Long id = 1L;
        when(personaRepository.existsById(id)).thenReturn(true);

        personaService.deletePersona(id);

        verify(personaRepository, times(1)).existsById(id);
        verify(personaRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeletePersona_PersonaNotFound() {
        Long id = 1L;
        when(personaRepository.existsById(id)).thenReturn(false);

        assertThrows(ClassNotFoundException.class, () -> {
            personaService.deletePersona(id);
        });

        verify(personaRepository, times(1)).existsById(id);
        verify(personaRepository, never()).deleteById(any());
    }

    @Test
    public void testGetPersonaById_Success() throws ClassNotFoundException {
        Long id = 1L;
        Persona persona = new Persona();
        when(personaRepository.findById(id)).thenReturn(Optional.of(persona));

        Persona result = personaService.getPersonaById(id);

        assertNotNull(result);
        assertEquals(persona, result);
        verify(personaRepository, times(1)).findById(id);
    }

    @Test
    public void testGetPersonaById_PersonaNotFound() {
        Long id = 1L;
        when(personaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ClassNotFoundException.class, () -> {
            personaService.getPersonaById(id);
        });

        verify(personaRepository, times(1)).findById(id);
    }

    @Test
    public void testGetPersonasByNombre() {
        String nombre = "John";
        List<Persona> personas = new ArrayList<>();
        when(personaRepository.findByNombre(nombre)).thenReturn(personas);

        List<Persona> result = personaService.getPersonasByNombre(nombre);

        assertNotNull(result);
        assertEquals(personas, result);
        verify(personaRepository, times(1)).findByNombre(nombre);
    }
}
