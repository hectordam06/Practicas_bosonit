package com.bosonit.crudrelaciones.controller;

import com.bosonit.crudrelaciones.application.PersonaServicio;
import com.bosonit.crudrelaciones.controller.dto.PersonaInputDto;
import com.bosonit.crudrelaciones.controller.dto.PersonaOutputDto;
import com.bosonit.crudrelaciones.controller.dto.PersonaRolOutputDto;
import com.bosonit.crudrelaciones.domain.LoginRequest;
import com.bosonit.crudrelaciones.domain.Persona;
import com.bosonit.crudrelaciones.exceptions.EntityNotFoundException;
import com.bosonit.crudrelaciones.repository.PersonaRepository;
import com.bosonit.crudrelaciones.repository.PersonaRepositoryCustom;
import com.bosonit.crudrelaciones.security.JwtResponse;
import com.bosonit.crudrelaciones.security.JwtTokenUtil;
import com.bosonit.crudrelaciones.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "https://codepen.io")
public class PersonaController {

    @Autowired
    PersonaServicio personaServicio;
    @Autowired
    PersonaRepositoryCustom personaRepositoryCustom;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Autenticar usuario
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generar token JWT
            final UserDetails userDetails = jwtUserDetailsService
                    .loadUserByUsername(loginRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);

            // Devolver token JWT en la respuesta
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            // Manejar excepción de autenticación fallida
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @GetMapping("/{id_persona}")
    public ResponseEntity<PersonaRolOutputDto> getPersonaById(@PathVariable int id_persona) {
        // Obtener detalles del usuario autenticado
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Verificar si el usuario es un administrador
        if (!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            // Si el usuario no es un administrador, devolver un error de acceso no autorizado
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Si el usuario es un administrador, permitir el acceso al endpoint
        return ResponseEntity.ok().body(personaServicio.getPersonaById(id_persona,false));
    }
    @GetMapping("/nombre/{name}")
    public List<PersonaRolOutputDto> getAllPersonasByNameLike(
            @PathVariable String nombre,
            @RequestParam(defaultValue = "false") boolean rol) {
        List<PersonaRolOutputDto> personas = (List<PersonaRolOutputDto>) personaServicio.getPersonasByName(nombre, rol);

        if (personas.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron personas con el nombre: " + nombre);
        }
        return personas;
    }

    @GetMapping
    public List<PersonaRolOutputDto> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize,
            @RequestParam(defaultValue = "false") boolean rol) {
        List<PersonaRolOutputDto> personas = (List<PersonaRolOutputDto>) personaServicio.getAllPersonas(pageNumber, pageSize, rol);

        if (personas.isEmpty()) {
            throw new EntityNotFoundException("No hay personas registradas.");
        }
        return personas;
    }

    @DeleteMapping("/{id_persona}")
    public void deletePersonaById(@PathVariable int id_persona)
    {
        personaServicio.deletePersonaById(id_persona);
    }

    @PutMapping("/{id_persona}")
    public ResponseEntity<PersonaOutputDto> updatePersona(
            @PathVariable int id_persona,
            @RequestBody(required = false) PersonaInputDto personaInputDto)
    {
        return ResponseEntity.ok(personaServicio.updatePersona(personaInputDto));

    }


    @GetMapping("/search")
    public ResponseEntity<Page<PersonaOutputDto>> searchPersonas(
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate,
            @RequestParam(required = false, defaultValue = "user") String orderBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Persona> personas = personaRepositoryCustom.searchWithPagination(user, name, surname, startDate, endDate, orderBy, page, size);
        Page<PersonaOutputDto> personasOutputDto = personas.map(persona -> persona.personaToPersonaOutputDto());
        return ResponseEntity.ok(personasOutputDto);
    }
}

