package com.bosonit.BackWeb_TPV.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class ReservaInputDto {
    private Long id;
    private String ciudadDestino;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correoElectronico;
    private Date fecha;
    private float hora;
    private String mensaje;

}
