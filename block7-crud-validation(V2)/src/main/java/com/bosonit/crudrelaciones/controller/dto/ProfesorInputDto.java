package com.bosonit.crudrelaciones.controller.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorInputDto {
    private int id_profesor;
    private String comments;
    private String branch;
    private int id_persona;
}