package com.bosonit.BackWeb_TPV.controller;

import com.bosonit.BackWeb_TPV.config.KafkaProducer;
import com.bosonit.BackWeb_TPV.controller.dto.AutobusOutputDto;
import com.bosonit.BackWeb_TPV.controller.dto.ReservaInputDto;
import com.bosonit.BackWeb_TPV.controller.dto.ReservaOutputDto;
import com.bosonit.BackWeb_TPV.domain.Autobus;
import com.bosonit.BackWeb_TPV.service.AutobusService;
import com.bosonit.BackWeb_TPV.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private AutobusService autobusService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/realizar")
    public ResponseEntity<?> realizarReserva(@RequestBody ReservaInputDto reservaInputDto) {

        ReservaOutputDto reservaOutputDto = reservaService.realizarReserva(reservaInputDto);

        if (reservaOutputDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AutobusOutputDto("No se pudo realizar la reserva"));
        }

        Autobus autobus = autobusService.obtenerAutobusPorDestinoFechaHora(reservaOutputDto.getCiudadDestino(), reservaOutputDto.getFecha(), reservaOutputDto.getHora());
        AutobusOutputDto autobusOutputDto = autobusService.actualizarPlazasDisponibles(autobus.getId());

        if (autobusOutputDto.getCapacidad() > 0) {
            kafkaProducer.enviarReserva(reservaOutputDto);
            return ResponseEntity.ok(autobusOutputDto);
        } else {
            reservaService.eliminarReserva(reservaOutputDto.getId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AutobusOutputDto("No hay plazas disponibles en el autobús para este viaje."));
        }
    }

    @GetMapping("/verificar-disponibilidad")
    public ResponseEntity<?> verificarDisponibilidad(@RequestParam String ciudadDestino,
                                                     @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date fecha,
                                                     @RequestParam float hora) {
        ReservaOutputDto respuesta = reservaService.verificarDisponibilidadPlazas(ciudadDestino, fecha, hora);
        return respuesta != null ? ResponseEntity.ok().body(respuesta) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReservaOutputDto("No hay disponibilidad de plazas para la fecha y hora especificadas"));
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarReserva(@PathVariable Long id) {
        ReservaOutputDto respuesta = reservaService.eliminarReserva(id);

    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> modificarReserva(@PathVariable Long id, @RequestBody ReservaInputDto reservaActualizada) {
        ReservaOutputDto respuesta = reservaService.modificarReserva(id, reservaActualizada);
        return respuesta != null ? ResponseEntity.ok().body(respuesta) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReservaOutputDto("Reserva no encontrada"));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ReservaOutputDto>> buscarReservasExistente(@RequestParam String ciudadDestino,
                                                                          @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date fecha,
                                                                          @RequestParam int hora) {
        List<ReservaOutputDto> reservas = reservaService.buscarReservasExistente(ciudadDestino, fecha, hora);
        return ResponseEntity.ok().body(reservas);
    }
}
