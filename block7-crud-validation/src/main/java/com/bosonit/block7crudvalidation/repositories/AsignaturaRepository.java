package com.bosonit.block7crudvalidation.repositories;

import com.bosonit.block7crudvalidation.models.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignaturaRepository extends JpaRepository<Asignatura,String> {

    @Query("SELECT a FROM Asignatura a JOIN a.estudiantes e WHERE e.id = :idEstudiante")
    List<Asignatura> findByEstudiantesId(@Param("idEstudiante") String idEstudiante);

}
