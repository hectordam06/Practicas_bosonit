package com.bosonit.crudrelaciones.security;

import com.bosonit.crudrelaciones.domain.Persona;
import com.bosonit.crudrelaciones.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Persona persona = personaRepository.findByUsuario(username);
        if (persona == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el nombre: " + username);
        }
        return new User(persona.getUsuario(), persona.getPassword(), Collections.emptyList());
    }
}

