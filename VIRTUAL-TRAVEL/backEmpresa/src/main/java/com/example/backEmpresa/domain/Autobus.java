package com.example.backEmpresa.domain;

import com.example.backEmpresa.controller.dto.AutobusInputDto;
import com.example.backEmpresa.controller.dto.AutobusOutputDto;
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
    private Integer capacidad ;

    public Autobus(AutobusInputDto autobus){
        this.id= autobus.getId();
        this.destino=autobus.getDestino();
        this.fecha=autobus.getFecha();
        this.hora=autobus.getHora();
        this.capacidad=autobus.getCapacidad();


    }
    public AutobusOutputDto autobusToautobusOutputDto(){
        return new AutobusOutputDto(
                this.id,
                this.destino,
                this.fecha,
                this.hora,
                this.capacidad
        );
    }

    public com.virtualtravel.common.Autobus transform(){
        return new com.virtualtravel.common.Autobus(
                this.id,
                this.destino,
                this.fecha,
                this.hora,
                this.capacidad
        );
    }
}
