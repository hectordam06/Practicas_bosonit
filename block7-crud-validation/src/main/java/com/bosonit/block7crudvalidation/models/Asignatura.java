package com.bosonit.block7crudvalidation.models;

import com.bosonit.block7crudvalidation.controlles.dto.asignatura.AsignaturaInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.asignatura.AsignaturaOutputDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "ASIGNATURA")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_asignatura")
    private String idAsignatura;
    @ManyToMany
    @JoinTable(
            name = "estudiante_asignatura",
            joinColumns = @JoinColumn(name = "id_asignatura"),
            inverseJoinColumns = @JoinColumn(name = "id_student")
    )
    private List<Student>estudiantes;
    private String asignatura;
    private String coments;
    @Column(name = "initial_date")
    private Date initialDate;
    @Column(name = "finish_date")
    private Date finishDate;

    public Asignatura (AsignaturaInputDto asignaturaInputDto){
        this.idAsignatura = String.valueOf(asignaturaInputDto.getIdAsignatura());
        this.asignatura= asignaturaInputDto.getAsignatura();
        this.coments = asignaturaInputDto.getComents();
        this.initialDate = asignaturaInputDto.getInitialDate();
        this.finishDate = asignaturaInputDto.getFinishDate();
    }
    public AsignaturaOutputDto asignaturatoasignaturaOutputDto(){
        return new AsignaturaOutputDto(
                this.idAsignatura,
                this.asignatura,
                this.coments,
                this.initialDate,
                this.finishDate,
                this.estudiantes
        );
    }

}
