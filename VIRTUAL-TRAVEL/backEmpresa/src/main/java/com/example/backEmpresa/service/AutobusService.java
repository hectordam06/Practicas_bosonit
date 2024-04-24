package com.example.backEmpresa.service;

import com.example.backEmpresa.controller.dto.AutobusOutputDto;
import com.example.backEmpresa.controller.dto.ReservaOutputDto;
import com.example.backEmpresa.domain.Autobus;
import com.virtualtravel.common.Reserva;

import java.util.Date;
import java.util.Optional;


public interface AutobusService {

         AutobusOutputDto obtenerAutobusPorId(int id);

        Integer obtenerPlazasDisponibles(String destino, Date fecha, float hora);

    AutobusOutputDto actualizarPlazasDisponibles(int id);
        Autobus obtenerAutobusPorDestinoFechaHora( String destino, Date fecha,  float hora);
        void confirmarReserva(Reserva reservaOutputDto);
    }

