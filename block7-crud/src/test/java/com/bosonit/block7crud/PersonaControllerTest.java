package com.bosonit.block7crud;

import com.bosonit.block7crud.controllers.PersonaController;
import com.bosonit.block7crud.entity.Persona;
import com.bosonit.block7crud.services.PersonaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class PersonaControllerTest {

    @Mock
    private PersonaService personaService;

    @InjectMocks
    private PersonaController personaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPersona() {
        Persona persona = new Persona();
        persona.setId(1L);
        persona.setNombre("Manolo");
        persona.setPoblacion("Medinaceli");
        ResponseEntity<String> responseEntity = personaController.addPersona(persona);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Persona añadida correctamente.", responseEntity.getBody());
        verify(personaService, times(1)).addPersona(persona);
    }

    @Test
    public void testUpdatePersona() throws ClassNotFoundException {
        Long id = 1L;
        Persona personaDetails = new Persona();
        personaDetails.setId(1L);
        personaDetails.setNombre("Manolo");
        personaDetails.setPoblacion("Medinaceli");

        Persona updatedPersona = new Persona();
        updatedPersona.setId(1L);
        updatedPersona.setNombre("Antonio");
        updatedPersona.setPoblacion("Ciudad Antigua");

        when(personaService.updatePersona(id, personaDetails)).thenReturn(updatedPersona);
        Persona result = personaController.updatePersona(id, personaDetails);
        assertNotNull(result);
        verify(personaService, times(1)).updatePersona(id, personaDetails);
    }

    @Test
    public void testDeletePersona() throws ClassNotFoundException {
        Long id = 1L;
        personaController.deletePersona(id);
        verify(personaService, times(1)).deletePersona(id);
    }

    @Test
    public void testGetPersonaById_Success() throws ClassNotFoundException {
        Long id = 1L;
        Persona persona = new Persona();
        persona.setId(1L);
        persona.setNombre("Manolo");
        persona.setPoblacion("Medinaceli");
        when(personaService.getPersonaById(id)).thenReturn(persona);
        ResponseEntity<?> responseEntity = personaController.getPersonaById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(persona, responseEntity.getBody());
    }

    @Test
    public void testGetPersonaById_NotFound() throws ClassNotFoundException {
        Long id = 1L;
        when(personaService.getPersonaById(id)).thenThrow(new ClassNotFoundException());
        ResponseEntity<?> responseEntity = personaController.getPersonaById(id);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Persona no encontrada.", responseEntity.getBody());
    }

    @Test
    public void testGetPersonasByNombre() {
        String nombre = "Aurelio";

        List<Persona> personas = new ArrayList<>();
        Persona persona1 = new Persona();
        persona1.setId(1L);
        persona1.setNombre("Aurelio");
        persona1.setPoblacion("Jerez de la frontera");

        Persona persona2 = new Persona();
        persona2.setId(2L);
        persona2.setNombre("Aurelio");
        persona2.setPoblacion("Soria");


        personas.add(persona1);
        personas.add(persona2);

        when(personaService.getPersonasByNombre(nombre)).thenReturn(personas);
        List<Persona> result = personaController.getPersonasByNombre(nombre);
        assertNotNull(result);
        assertEquals(personas, result);
    }

    @Test
    public void testGetAllPersonas() {
        List<Persona> personas = new ArrayList<>();
        Persona persona1 = new Persona();
        persona1.setId(1L);
        persona1.setNombre("Aurelio");
        persona1.setPoblacion("Jerez de la frontera");

        Persona persona2 = new Persona();
        persona2.setId(2L);
        persona2.setNombre("Aurelio");
        persona2.setPoblacion("Soria");


        personas.add(persona1);
        personas.add(persona2);

        when(personaService.getAllPersonas()).thenReturn(personas);
        List<Persona> result = personaController.getAllPersonas();
        assertNotNull(result);
        assertEquals(personas, result);
    }
}
