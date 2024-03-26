package com.bosonit.crudrelaciones.repository;

import com.bosonit.crudrelaciones.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository <Persona, Integer>, PagingAndSortingRepository<Persona, Integer> {
    List<Persona> findByNameLike(String name);

    Persona findByUsuario(String username);
}
