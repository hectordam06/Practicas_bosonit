package com.bosonit.crudrelaciones.domain;

import com.bosonit.crudrelaciones.controller.dto.EstudianteFullOutputDto;
import com.bosonit.crudrelaciones.controller.dto.EstudianteInputDto;
import com.bosonit.crudrelaciones.controller.dto.EstudianteSimpleOutputDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
    @Id
    @GeneratedValue
    private int id_estudiante;

    @Column(name = "horas_por_semana", nullable = false)
    private int num_hours_week;

    @Column(name = "comentarios")
    private String comments;

    @Column(name = "rama", nullable = false)
    private String branch;

    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona", nullable = false, unique = true)
    private Persona persona;

    @ManyToMany(mappedBy = "estudiantes")
    private Set<Asignatura> asignaturas;

    @ManyToOne
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")
    private Profesor profesor;

    public Estudiante(EstudianteInputDto estudianteInputDto){
        this.num_hours_week = estudianteInputDto.getNum_hours_week();
        this.branch = estudianteInputDto.getBranch();
        this.comments = estudianteInputDto.getComments();
    }

    public EstudianteSimpleOutputDto SimplePersonaToEstudiante_EstudianteOutPutDto(){
        return new EstudianteSimpleOutputDto(
                this.id_estudiante,
                this.num_hours_week,
                this.branch,
                this.comments,
                this.persona.getId_persona(),
                this.persona.getName(),
                this.persona.getSurname()
        );
    }

    public EstudianteFullOutputDto FullPersonaToEstudiante_EstudianteOutPutDto(){
        return new EstudianteFullOutputDto(
                this.id_estudiante,
                this.num_hours_week,
                this.comments,
                this.branch,
                this.persona.getId_persona(),
                this.persona.getName(),
                this.persona.getSurname(),
                this.persona.getCompany_email(),
                this.persona.getPersonal_email(),
                this.persona.getCity(),
                this.persona.getActive(),
                this.persona.getCreated_date(),
                this.persona.getImagen_url(),
                this.persona.getTermination_date()
        );
    }
}