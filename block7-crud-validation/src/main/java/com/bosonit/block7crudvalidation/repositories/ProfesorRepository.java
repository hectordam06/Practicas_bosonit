package com.bosonit.block7crudvalidation.repositories;

import com.bosonit.block7crudvalidation.models.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor,String> {
    List<Profesor> findByPersonaId(String idPersona);

}
