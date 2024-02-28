package com.bosonit.block7crudvalidation.services;

import com.bosonit.block7crudvalidation.controlles.dto.asignatura.AsignaturaOutputDto;
import com.bosonit.block7crudvalidation.controlles.dto.student.StudentInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.student.StudentOutpuDto;
import com.bosonit.block7crudvalidation.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface StudentService {
    StudentOutpuDto getStudentById(String id);

    StudentOutpuDto addStudent(StudentInputDto studentInputDto);

    List<StudentOutpuDto> getAllStudents();

    StudentOutpuDto updateStudent(String id, StudentInputDto studentInputDto);

    void deleteStudent(String id);

    StudentOutpuDto asignarAsignatura(List<String> idAsignatura, String idEstudiante);
    StudentOutpuDto desasignarAsignaturas(List<String> idAsignaturas, String idEstudiante);

    List<AsignaturaOutputDto> getAsignaturasByStudentId(String idEstudiante);

    boolean deleteAsignatura(String idAsignatura);
}
