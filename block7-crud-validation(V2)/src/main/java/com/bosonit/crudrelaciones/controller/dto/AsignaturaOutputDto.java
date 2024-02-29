package com.bosonit.crudrelaciones.controller.dto;

import lombok.*;

import java.util.Date;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaOutputDto {
    private int id_asignatura;
    private String asignatura;
    private String comments;
    private Date initial_date;
    private Date finish_date;
    private Set<Integer> id_estudiantes;
}