package com.bosonit.block7crudvalidation.repositories;

import com.bosonit.block7crudvalidation.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona,String> {
Persona findByUsuario(String usuario);

}
