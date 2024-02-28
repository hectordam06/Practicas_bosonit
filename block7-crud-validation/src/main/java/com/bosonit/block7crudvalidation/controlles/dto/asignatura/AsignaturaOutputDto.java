package com.bosonit.block7crudvalidation.controlles.dto.asignatura;

import com.bosonit.block7crudvalidation.models.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data

@NoArgsConstructor
public class AsignaturaOutputDto {
    private String idAsignatura;
    private String asignatura;
    private String coments;
    private Date initialDate;
    private Date finishDate;
    private List<Student> estudiantes;


    public AsignaturaOutputDto(String idAsignatura, String asignatura, String coments, Date initialDate, Date finishDate, List<Student> estudiantes) {
        this.idAsignatura = idAsignatura;
        this.asignatura = asignatura;
        this.coments = coments;
        this.initialDate = initialDate;
        this.finishDate = finishDate;
        this.estudiantes = estudiantes;
    }

    // Getters y setters
    public String getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(String idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getComents() {
        return coments;
    }

    public void setComents(String coments) {
        this.coments = coments;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public List<Student> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Student> estudiantes) {
        this.estudiantes = estudiantes;
    }
    }


