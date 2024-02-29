package com.bosonit.crudrelaciones.controller.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorOutputDto {
    private int id_profesor;
    private String comments;
    private String branch;
    private int id_persona;
    private String name;
    private String surname;
}