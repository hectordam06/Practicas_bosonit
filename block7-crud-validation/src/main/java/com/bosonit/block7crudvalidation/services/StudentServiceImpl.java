package com.bosonit.block7crudvalidation.services;

import com.bosonit.block7crudvalidation.controlles.dto.asignatura.AsignaturaOutputDto;
import com.bosonit.block7crudvalidation.controlles.dto.student.StudentInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.student.StudentOutpuDto;
import com.bosonit.block7crudvalidation.models.Asignatura;
import com.bosonit.block7crudvalidation.models.Student;
import com.bosonit.block7crudvalidation.repositories.AsignaturaRepository;
import com.bosonit.block7crudvalidation.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl  implements  StudentService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
     AsignaturaRepository asignaturaRepository;
    @Override
    public StudentOutpuDto getStudentById(String id) {

        return studentRepository.findById(id).get().studenttoStudentOutpuDto();
    }

    @Override
    public StudentOutpuDto addStudent(StudentInputDto studentInputDto) {

        return  studentRepository.save(new Student(studentInputDto)).studenttoStudentOutpuDto();

    }

    @Override
    public List<StudentOutpuDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(Student::studenttoStudentOutpuDto)
                .toList();

    }

    @Override
    public StudentOutpuDto updateStudent(String id, StudentInputDto studentInputDto) {
        Student student = studentRepository.findById(id).orElse(null);


        student.setPersona(studentInputDto.getPersona());
        student.setNumHoursWeek(studentInputDto.getNumHoursWeek());
        student.setComents(studentInputDto.getComents());
        student.setProfesor(studentInputDto.getProfesor());
        student.setBranch(studentInputDto.getBranch());

        return studentRepository.save(student).studenttoStudentOutpuDto();

    }


    @Override
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentOutpuDto asignarAsignatura(List<String> idAsignatura, String idEstudiante) {
        Student student = studentRepository.findById(idEstudiante).orElseThrow(() -> new RuntimeException("error"));

            List<Asignatura> asignaturas = asignaturaRepository.findAllById(idAsignatura);
            student.getAsignaturas().addAll(asignaturas);
            Student updatedStudent = studentRepository.save(student);
            return updatedStudent.studenttoStudentOutpuDto();

    }

    @Override
    public StudentOutpuDto desasignarAsignaturas(List<String> idAsignaturas, String idEstudiante) {
        Student student = studentRepository.findById(idEstudiante).orElseThrow(()->new RuntimeException(""));

            List<Asignatura> asignaturasToRemove = asignaturaRepository.findAllById(idAsignaturas);
            student.getAsignaturas().removeAll(asignaturasToRemove);
            Student updatedStudent = studentRepository.save(student);
            return updatedStudent.studenttoStudentOutpuDto();
        }

    @Override
    public List<AsignaturaOutputDto> getAsignaturasByStudentId(String idEstudiante) {
        Student student = studentRepository.findById(idEstudiante).orElse(null);
        if (student != null) {
            return student.getAsignaturas().stream()
                    .map(Asignatura::asignaturatoasignaturaOutputDto)
                    .toList();
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteAsignatura(String idAsignatura) {
        Asignatura asignatura = asignaturaRepository.findById(idAsignatura).orElse(null);
        if (asignatura != null) {
            if (asignatura.getEstudiantes().isEmpty()) {
                asignaturaRepository.deleteById(idAsignatura);
                return true;
            } else {
                throw new RuntimeException("No se puede eliminar la asignatura porque tiene estudiantes asignados.");
            }
        } else {
            throw new RuntimeException("No se encontró la asignatura con el ID proporcionado.");
        }
    }
}

