package com.bosonit.crudrelaciones.controller.dto;

import lombok.*;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaInputDto {
    int id_persona;
    String usuario;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    Boolean active;
    Date created_date;
    String imagen_url;
    Date termination_date;
}