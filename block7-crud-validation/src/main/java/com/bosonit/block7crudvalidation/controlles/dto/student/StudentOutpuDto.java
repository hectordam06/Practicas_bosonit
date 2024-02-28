package com.bosonit.block7crudvalidation.controlles.dto.student;

import com.bosonit.block7crudvalidation.models.Asignatura;
import com.bosonit.block7crudvalidation.models.Persona;
import com.bosonit.block7crudvalidation.models.Profesor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutpuDto {
    private int idStudent;
    private Persona persona;
    private int numHoursWeek;
    private String coments;
    private Profesor profesor;
    private String branch;
    private List<Asignatura> asignaturas;
}
