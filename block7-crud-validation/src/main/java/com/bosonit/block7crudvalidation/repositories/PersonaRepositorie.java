package com.bosonit.block7crudvalidation.repositories;

import com.bosonit.block7crudvalidation.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepositorie extends JpaRepository<Persona,Integer> {
Persona findByUsuario(String usuario);

}
