package com.bosonit.block7crudvalidation.controlles.dto.asignatura;

import com.bosonit.block7crudvalidation.models.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaInputDto {
    private String idAsignatura;
    private String asignatura;
    private String coments;
    private Date initialDate;
    private Date finishDate;

    @Getter
    private List<Student> estudiantes;

}