package com.bosonit.block7crudvalidation.controlles.dto.profesor;

import com.bosonit.block7crudvalidation.models.Persona;
import com.bosonit.block7crudvalidation.models.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorInputDto {
    private String id_profesor;
    private String id_persona;
    private String coments;
    private String branch;
/*
    @Getter
    private List<Student> estudiantes;

 */
}
