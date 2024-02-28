package com.bosonit.block7crudvalidation.controlles.dto.persona;
import java.util.Date;

public class PersonaWithRoleDto {
    private String idPersona;
    private String usuario;
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

    // Constructor
    public PersonaWithRoleDto(String idPersona, String usuario, String name, String surname, String companyEmail, String personalEmail, String city, boolean active, Date createdDate, String imageUrl, Date terminationDate, boolean esProfesor) {
        this.idPersona = idPersona;
        this.usuario = usuario;
        this.name = name;
        this.surname = surname;
        this.companyEmail = companyEmail;
        this.personalEmail = personalEmail;
        this.city = city;
        this.active = active;
        this.createdDate = createdDate;
        this.imageUrl = imageUrl;
        this.terminationDate = terminationDate;
        this.esProfesor = esProfesor;
    }

    // Getters y setters
    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public boolean isEsProfesor() {
        return esProfesor;
    }

    public void setEsProfesor(boolean esProfesor) {
        this.esProfesor = esProfesor;
    }
}
