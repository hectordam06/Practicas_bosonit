package com.bosonit.BackWeb_TPV.domain;

import com.bosonit.BackWeb_TPV.controller.dto.AutobusOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Autobus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String destino;
    private Date fecha;
    private float hora;
    private int capacidad ;


    public AutobusOutputDto autobusToautobusOutputDto(){
        return new AutobusOutputDto(
                this.id,
                this.destino,
                this.fecha,
                this.hora,
                this.capacidad
        );

    }

    }

