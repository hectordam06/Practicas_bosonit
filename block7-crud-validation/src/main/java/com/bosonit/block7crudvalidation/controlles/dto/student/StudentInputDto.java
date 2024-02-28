package com.bosonit.block7crudvalidation.controlles.dto.student;

import com.bosonit.block7crudvalidation.models.Persona;
import com.bosonit.block7crudvalidation.models.Profesor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDto {
    private int idStudent;
    private Persona persona;
    private int numHoursWeek;
    private String coments;
    private Profesor profesor;
    private String branch;

}
