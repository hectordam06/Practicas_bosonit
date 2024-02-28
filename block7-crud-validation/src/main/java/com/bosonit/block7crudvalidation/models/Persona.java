package com.bosonit.block7crudvalidation.models;

import com.bosonit.block7crudvalidation.controlles.dto.persona.PersonaInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.persona.PersonaOutputDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@Table(name = "PERSONA")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_persona;
    @Column(nullable = false,length = 10)
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;
    private boolean esProfesor;

   public Persona (PersonaInputDto personaInputDto){
         this.id_persona = String.valueOf(personaInputDto.getId_persona());
        this.usuario = personaInputDto.getUsuario();
        this.password = personaInputDto.getPassword();
        this.name = personaInputDto.getName();
        this.surname = personaInputDto.getSurname();
        this.companyEmail = personaInputDto.getCompanyEmail();
        this.personalEmail = personaInputDto.getPersonalEmail();
        this.city = personaInputDto.getCity();
        this.active = personaInputDto.isActive();
        this.createdDate = personaInputDto.getCreatedDate();
        this.imageUrl = personaInputDto.getImageUrl();
        this.terminationDate = personaInputDto.getTerminationDate();
    }
    public PersonaOutputDto personatoPersonaOutputDto(){
        return new PersonaOutputDto(
                this.id_persona,
                this.usuario,
                this.name,
                this.surname,
                this.companyEmail,
                this.personalEmail,
                this.city,
                this.active,
                this.createdDate,
                this.imageUrl,
                this.terminationDate
        );
    }

    public String getId() {
        return id_persona;
    }

    public String getIdPersona() {
        return id_persona;
    }
}
