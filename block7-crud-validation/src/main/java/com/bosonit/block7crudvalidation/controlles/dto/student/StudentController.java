package com.bosonit.block7crudvalidation.controlles.dto.student;

import com.bosonit.block7crudvalidation.controlles.dto.asignatura.AsignaturaOutputDto;
import com.bosonit.block7crudvalidation.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentOutpuDto> getStudentById(@PathVariable String id) {
        StudentOutpuDto student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<StudentOutpuDto> addStudent(@RequestBody StudentInputDto studentInputDto) {
        StudentOutpuDto addedStudent = studentService.addStudent(studentInputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedStudent);
    }

    @GetMapping
    public ResponseEntity<List<StudentOutpuDto>> getAllStudents() {
        List<StudentOutpuDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentOutpuDto> updateStudent(@PathVariable String id, @RequestBody StudentInputDto studentInputDto) {
        StudentOutpuDto updatedStudent = studentService.updateStudent(id, studentInputDto);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idEstudiante}/asignar-asignatura")
    public ResponseEntity<StudentOutpuDto> asignarAsignatura(@PathVariable String idEstudiante, @RequestBody List<String> idAsignaturas) {
        StudentOutpuDto updatedStudent = studentService.asignarAsignatura(idAsignaturas, idEstudiante);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idEstudiante}/desasignar-asignaturas")
    public ResponseEntity<StudentOutpuDto> desasignarAsignaturas(@PathVariable String idEstudiante, @RequestBody List<String> idAsignaturas) {
        StudentOutpuDto updatedStudent = studentService.desasignarAsignaturas(idAsignaturas, idEstudiante);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idEstudiante}/asignaturas")
    public ResponseEntity<List<AsignaturaOutputDto>> getAsignaturasByStudentId(@PathVariable String idEstudiante) {
        List<AsignaturaOutputDto> asignaturas = studentService.getAsignaturasByStudentId(idEstudiante);
        if (!asignaturas.isEmpty()) {
            return ResponseEntity.ok(asignaturas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/asignaturas/{idAsignatura}")
    public ResponseEntity<Void> deleteAsignatura(@PathVariable String idAsignatura) {
        boolean deleted = studentService.deleteAsignatura(idAsignatura);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}