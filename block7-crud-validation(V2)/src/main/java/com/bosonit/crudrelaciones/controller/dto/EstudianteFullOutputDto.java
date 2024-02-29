package com.bosonit.crudrelaciones.controller.dto;

import lombok.*;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteFullOutputDto extends EstudianteSimpleOutputDto{

    PersonaOutputDto personaOutputDto;

    public EstudianteFullOutputDto
            (int idEstudiante,
             int numHoursWeek,
             String comments,
             String branch,
             int idPersona,
             String name,
             String surname,
             String companyEmail,
             String personalEmail,
             String city,
             Boolean active,
             Date createdDate,
             String imagenUrl,
             Date terminationDate) {
    }
}