package com.bosonit.block7crudvalidation.models;

import com.bosonit.block7crudvalidation.controlles.dto.profesor.ProfesorInputDto;
import com.bosonit.block7crudvalidation.controlles.dto.profesor.ProfesorOutputDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "PROFESOR")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_profesor")
    private String id_profesor;
    @OneToMany(mappedBy = "profesor")
    private List<Student> estudiantes;
    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private Persona persona;
    private String coments;
    private String branch;

    public Profesor(ProfesorInputDto profesorInputDto){
        this.id_profesor = String.valueOf(profesorInputDto.getId_profesor());

        this.coments = profesorInputDto.getComents();
        this.branch = profesorInputDto.getBranch();
    }
    public ProfesorOutputDto profesortoProfesorOutputDto(){
        return new ProfesorOutputDto(
                this.id_profesor,
                this.persona,
                this.coments,
                this.branch,
                this.estudiantes
        );
    }
}
