package com.bosonit.block7crud.repositories;

import com.bosonit.block7crud.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long> {
    List<Persona> findByNombre(String nombre);
}
