package com.bosonit.BackWeb_TPV.service;

import com.bosonit.BackWeb_TPV.controller.dto.ReservaInputDto;
import com.bosonit.BackWeb_TPV.controller.dto.ReservaOutputDto;
import com.bosonit.BackWeb_TPV.domain.Reserva;
import com.bosonit.BackWeb_TPV.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public ReservaOutputDto findReservaByID(Long id) {
        return reservaRepository.findById(id).orElseThrow().reservaToReservaOutputDto();
    }

    @Override
    public ReservaOutputDto realizarReserva(ReservaInputDto reservaInputDto) {
     /*   // Verificar la disponibilidad de plazas
        if (verificarDisponibilidadPlazas(reservaInputDto.getCiudadDestino())) {/*, reservaInputDto.getFecha(), (int) reservaInputDto.getHora()
            // Convertir ReservaInputDto a Reserva
            Reserva reserva = convertirAEntidad(reservaInputDto);
            // Guardar la reserva
        */
            return reservaRepository.save(new Reserva(reservaInputDto)).reservaToReservaOutputDto();
        }


    @Override
    public ReservaOutputDto verificarDisponibilidadPlazas(String ciudadDestino, Date fecha, float hora) {
        List<Reserva> reservas = reservaRepository.findByCiudadDestinoAndFechaAndHora(ciudadDestino, fecha, hora);
        int plazasOcupadas = reservas.size();
        if (plazasOcupadas < 4) {
            return new ReservaOutputDto(); // Devuelve un objeto ReservaOutputDto vacío
        } else {
            return new ReservaOutputDto("No hay disponibilidad de plazas para la fecha y hora especificadas");
        }
    }







    @Override
    public List<ReservaOutputDto> buscarReservasExistente(String ciudadDestino,Date fecha,float hora){/*, Date fecha, int hora) {*/
        List<Reserva> reservas = reservaRepository.findByCiudadDestinoAndFechaAndHora(ciudadDestino, fecha, hora);/*);*/
        return reservas.stream()
                .map(Reserva::reservaToReservaOutputDto)
                .collect(Collectors.toList());
    }



    @Override
    public void eliminarReserva(Long id) {
          reservaRepository.findById(id).orElseThrow();
          reservaRepository.deleteById(id);



    }

    @Override
    public ReservaOutputDto modificarReserva(Long id, ReservaInputDto reservaActualizada) {
        Optional<Reserva> optionalReserva = reservaRepository.findById(id);
        if (optionalReserva.isPresent()) {
            Reserva reservaExistente = optionalReserva.get();
            reservaExistente.setCiudadDestino(reservaActualizada.getCiudadDestino());
            reservaExistente.setNombre(reservaActualizada.getNombre());
            reservaExistente.setApellidos(reservaActualizada.getApellidos());
            reservaExistente.setTelefono(reservaActualizada.getTelefono());
            reservaExistente.setCorreoElectronico(reservaActualizada.getCorreoElectronico());
            reservaExistente.setFecha(reservaActualizada.getFecha());
            reservaExistente.setHora(reservaActualizada.getHora());
            reservaRepository.save(reservaExistente);
            return reservaExistente.reservaToReservaOutputDto();
        }
        return null;
    }

    private Reserva convertirAEntidad(ReservaInputDto reservaInputDto) {
        Reserva reserva = new Reserva();
        reserva.setCiudadDestino(reservaInputDto.getCiudadDestino());
        reserva.setNombre(reservaInputDto.getNombre());
        reserva.setApellidos(reservaInputDto.getApellidos());
        reserva.setTelefono(reservaInputDto.getTelefono());
        reserva.setCorreoElectronico(reservaInputDto.getCorreoElectronico());
        reserva.setFecha(reservaInputDto.getFecha());
        reserva.setHora(reservaInputDto.getHora());
        return reserva;
    }
}
