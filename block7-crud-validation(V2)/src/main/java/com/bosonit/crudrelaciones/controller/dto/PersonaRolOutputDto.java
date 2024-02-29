package com.bosonit.crudrelaciones.controller.dto;

import lombok.*;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaRolOutputDto extends PersonaOutputDto{

    String rol;

    public PersonaRolOutputDto
            (int idPersona,
             String usuario,
             String name,
             String surname,
             String password,
             String companyEmail,
             String personalEmail,
             String city,
             Boolean active,
             Date createdDate,
             String imagenUrl,
             Date terminationDate,
             String rol) {
        super(idPersona, usuario, name, surname,password, companyEmail, personalEmail, city, active, createdDate, imagenUrl, terminationDate);
        this.rol = rol;
    }
}