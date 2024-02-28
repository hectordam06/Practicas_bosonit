package com.bosonit.block7crudvalidation.models;

import com.bosonit.block7crudvalidation.controlles.dto.student.StudentInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.student.StudentOutpuDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_student")
    private int idStudent;
    @ManyToMany(mappedBy = "estudiantes")
    private List<Asignatura> asignaturas;
    @OneToOne
    @JoinColumn(name = "id_persona",referencedColumnName = "id_persona")
    private Persona persona;
    @Column(name="num_hours_week")
    private int numHoursWeek;
    private String coments;
    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;
    private String branch;

    public Student(StudentInputDto studentInputDto){

        this.idStudent = studentInputDto.getIdStudent();

        this.numHoursWeek = studentInputDto.getNumHoursWeek();
        this.coments = studentInputDto.getComents();
        this.profesor = studentInputDto.getProfesor();
        this.branch = studentInputDto.getBranch();

    };
    public StudentOutpuDto studenttoStudentOutpuDto(){
        return new StudentOutpuDto(
                this.idStudent,
                this.persona,
                this.numHoursWeek,
                this.coments,
                this.profesor,
                this.branch,
                this.asignaturas
        );
    }
}
