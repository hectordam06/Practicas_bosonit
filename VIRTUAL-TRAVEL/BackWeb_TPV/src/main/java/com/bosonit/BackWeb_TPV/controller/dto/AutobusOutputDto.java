package com.bosonit.BackWeb_TPV.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class AutobusOutputDto {
    private int id;
    private String destino;
    private Date fecha;
    private double hora;
    private Integer capacidad;

    public AutobusOutputDto(String destino) {
    }
}
