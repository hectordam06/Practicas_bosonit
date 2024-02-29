package com.bosonit.crudrelaciones.domain;

import com.bosonit.crudrelaciones.controller.dto.AsignaturaInputDto;
import com.bosonit.crudrelaciones.controller.dto.AsignaturaOutputDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "asignatura")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asignatura {
    @Id
    @GeneratedValue
    private int id_asignatura;

    @ManyToMany
    @JoinTable(
            name = "asignatura_estudiante",
            joinColumns = @JoinColumn(name = "id_asignatura"),
            inverseJoinColumns = @JoinColumn(name = "id_estudiante")
    )
    private Set<Estudiante> estudiantes = new HashSet<>();
    private String asignatura;

    @Column(name = "comentarios")
    private String comments;

    @Column(name = "fecha_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date inital_date;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date finish_date;

    public Asignatura(AsignaturaInputDto asignaturaInputDto){
        this.asignatura = asignaturaInputDto.getAsignatura();
        this.comments = asignaturaInputDto.getComments();
        this.inital_date = asignaturaInputDto.getInitial_date();
        this.finish_date = asignaturaInputDto.getFinish_date();
    }
    public AsignaturaOutputDto asignaturaToAsignaturaOutputDto(){
        Set<Integer> id_estudiantes = this.estudiantes.stream().map(Estudiante::getId_estudiante).collect(Collectors.toSet());
        return new AsignaturaOutputDto(
                this.id_asignatura,
                this.asignatura,
                this.comments,
                this.inital_date,
                this.finish_date,
                id_estudiantes
        );
    }
}