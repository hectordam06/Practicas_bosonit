package com.bosonit.crudrelaciones.controller.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteSimpleOutputDto {
    private int id_estudiante;
    private int num_hours_week;
    private String comments;
    private String branch;
    private int id_persona;
    private String name;
    private String surname;
}