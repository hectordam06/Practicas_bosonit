package com.bosonit.block7crudvalidation.repositories;

import com.bosonit.block7crudvalidation.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query(value = "SELECT * FROM Student WHERE idAsignatura like %?1%", nativeQuery = true)
    List<Student> findByIdAsignaturaLike(String idAsignatura);
    List<Student> findByPersonaId(String idPersona);
}
