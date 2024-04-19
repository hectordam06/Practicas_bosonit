package com.bosonit.BackWeb_TPV.domain;

import com.bosonit.BackWeb_TPV.controller.dto.ReservaInputDto;
import com.bosonit.BackWeb_TPV.controller.dto.ReservaOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ciudadDestino;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correoElectronico;
    private Date fecha;
    private float hora;
    private String mensaje;

    public Reserva(ReservaInputDto reserva){
        this.id=reserva.getId();
        this.ciudadDestino=reserva.getCiudadDestino();
        this.nombre=reserva.getNombre();
        this.apellidos=reserva.getApellidos();
        this.telefono=reserva.getTelefono();
        this.correoElectronico=reserva.getCorreoElectronico();
        this.fecha=reserva.getFecha();
        this.hora=reserva.getHora();
    }
    public ReservaOutputDto reservaToReservaOutputDto(){
        return new ReservaOutputDto(
                this.id,
                this.ciudadDestino,
                this.nombre,
                this.apellidos,
                this.telefono,
                this.correoElectronico,
                this.fecha,
                this.hora,
                this.mensaje
        );
    }

}
