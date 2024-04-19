package com.example.backEmpresa.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class AutobusInputDto {
    private int id;
    private String destino;
    private Date fecha;
    private float hora;


}
