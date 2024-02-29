package com.bosonit.crudrelaciones.controller.dto;

import lombok.*;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaOutputDto {
    int id_persona;
    String usuario;
    String name;
    String surname;
    String password;
    String company_email;
    String personal_email;
    String city;
    Boolean active;
    Date created_date;
    String imagen_url;
    Date termination_date;
}