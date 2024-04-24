package com.bosonit.BackWeb_TPV.service;

import com.bosonit.BackWeb_TPV.controller.dto.AutobusOutputDto;
import com.bosonit.BackWeb_TPV.domain.Autobuss;
import com.bosonit.BackWeb_TPV.repositories.AutobusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class AutobusServiceImpl implements AutobusService {
    @Autowired

    AutobusRepository autobusRepository;


    @Override
    public AutobusOutputDto obtenerAutobusPorId(int id) {
        Autobuss autobus = autobusRepository.findById(id).orElseThrow();
        return autobus.autobusToautobusOutputDto();
    }

    @Override
    public Integer obtenerPlazasDisponibles(String destino, Date fecha, float hora) {
        return autobusRepository.obtenerPlazasDisponibles(destino, fecha, hora).getCapacidad();
    }

    @Override
    public AutobusOutputDto actualizarPlazasDisponibles(int id) {
        Autobuss autobus = autobusRepository.findById(id).orElseThrow();
        Integer plazas = (autobus.getCapacidad());

        if (plazas > 0) {
            autobus.setCapacidad( plazas - 1);
            autobusRepository.save(autobus);

            // Crear y devolver un objeto AutobusOutputDto con la información actualizada
            return new AutobusOutputDto(
                    autobus.getId(),
                    autobus.getDestino(),
                    autobus.getFecha(),
                    autobus.getHora(),
                    autobus.getCapacidad()
            );
        } else {

            return new AutobusOutputDto("No hay plazas disponibles en el autobús con ID: " + id);
        }
    }




    @Override
    public Autobuss obtenerAutobusPorDestinoFechaHora(String destino, Date fecha, float hora) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(fecha);

        return autobusRepository.obtenerAutobusPorDestinoFechaHora(
                destino,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH)+1,
                calendar.get(Calendar.DAY_OF_MONTH),
                hora);



    }
}
