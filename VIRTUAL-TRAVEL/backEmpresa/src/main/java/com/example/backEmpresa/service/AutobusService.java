package com.example.backEmpresa.service;

import com.example.backEmpresa.controller.dto.AutobusOutputDto;
import com.example.backEmpresa.controller.dto.ReservaOutputDto;
import com.example.backEmpresa.domain.Autobus;

import java.util.Date;


public interface AutobusService {

         AutobusOutputDto obtenerAutobusPorId(int id);

        Long obtenerPlazasDisponibles(String destino, Date fecha, float hora);

    AutobusOutputDto actualizarPlazasDisponibles(int id);
        Autobus obtenerAutobusPorDestinoFechaHora( String destino, Date fecha,  float hora);
        void confirmarReserva(ReservaOutputDto reservaOutputDto);
    }

