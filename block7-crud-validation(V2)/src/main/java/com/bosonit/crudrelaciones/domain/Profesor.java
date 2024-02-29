package com.bosonit.crudrelaciones.domain;

import com.bosonit.crudrelaciones.controller.dto.ProfesorInputDto;
import com.bosonit.crudrelaciones.controller.dto.ProfesorOutputDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profesor {
    @Id
    @GeneratedValue
    private int id_profesor;

    @Column(name = "comentarios")
    private String comments;

    @Column(name = "rama", nullable = false)
    private String branch;

    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona", nullable = false, unique = true)
    private Persona persona;

    @OneToMany(mappedBy = "profesor")
    private Set<Estudiante> estudiante;

    public Profesor(ProfesorInputDto profesorInputDto){
        this.id_profesor = profesorInputDto.getId_profesor();
        this.branch = profesorInputDto.getBranch();
        this.comments = profesorInputDto.getComments();
    }

    public ProfesorOutputDto PersonaToProfesor_ProfesorOutputDto(){
        return new ProfesorOutputDto(
                this.id_profesor,
                this.branch,
                this.comments,
                this.persona.getId_persona(),
                this.persona.getName(),
                this.persona.getSurname()
        );
    }
}