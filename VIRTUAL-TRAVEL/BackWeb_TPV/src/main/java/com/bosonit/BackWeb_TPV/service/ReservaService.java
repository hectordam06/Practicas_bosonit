package com.bosonit.BackWeb_TPV.service;

import com.bosonit.BackWeb_TPV.controller.dto.ReservaInputDto;
import com.bosonit.BackWeb_TPV.controller.dto.ReservaOutputDto;

import java.util.Date;
import java.util.List;

public interface ReservaService {
        ReservaOutputDto findReservaByID (Long id);
      ReservaOutputDto realizarReserva(ReservaInputDto reserva);

    ReservaOutputDto verificarDisponibilidadPlazas(String ciudadDestino, Date fecha, float hora);/*,) ;*/


     List<ReservaOutputDto> buscarReservasExistente(String ciudadDestino, Date fecha, float hora);/*,);*/

    void eliminarReserva(Long id) ;

    ReservaOutputDto modificarReserva(Long id, ReservaInputDto reservaActualizada) ;}



