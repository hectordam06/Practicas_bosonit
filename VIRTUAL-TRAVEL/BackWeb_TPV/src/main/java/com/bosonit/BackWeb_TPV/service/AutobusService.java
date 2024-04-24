package com.bosonit.BackWeb_TPV.service;

import com.bosonit.BackWeb_TPV.controller.dto.AutobusOutputDto;
import com.bosonit.BackWeb_TPV.domain.Autobuss;

import java.util.Date;

public interface AutobusService {
    AutobusOutputDto obtenerAutobusPorId(int id);

    Integer obtenerPlazasDisponibles(String destino, Date fecha, float hora);

    AutobusOutputDto actualizarPlazasDisponibles(int id);
    Autobuss obtenerAutobusPorDestinoFechaHora(String destino, Date fecha, float hora);
}
