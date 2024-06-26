package com.bosonit.BackWeb_TPV.service;

import com.bosonit.BackWeb_TPV.controller.dto.AutobusOutputDto;
import com.bosonit.BackWeb_TPV.domain.Autobus;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


public interface AutobusService {

         AutobusOutputDto obtenerAutobusPorId(int id);

        Long obtenerPlazasDisponibles(String destino, Date fecha, float hora);

    AutobusOutputDto actualizarPlazasDisponibles(int id);
        Autobus obtenerAutobusPorDestinoFechaHora( String destino, Date fecha,  float hora);
    }

