package com.bosonit.block7crudvalidation.controlles.dto.profesor;

import com.bosonit.block7crudvalidation.models.Persona;
import com.bosonit.block7crudvalidation.models.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorOutputDto {
    private String id_profesor;
    private Persona persona;
    private String coments;
    private String branch;
    private List<Student> estudiantes;

    public void setId(String id) {
        this.id_profesor = id;
    }
}
