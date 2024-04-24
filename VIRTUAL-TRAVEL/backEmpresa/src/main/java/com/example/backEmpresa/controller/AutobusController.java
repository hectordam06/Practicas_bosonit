package com.example.backEmpresa.controller;

import com.example.backEmpresa.confing.KafkaProducerConfig;
import com.example.backEmpresa.controller.dto.AutobusOutputDto;
import com.example.backEmpresa.controller.dto.ReservaOutputDto;
import com.example.backEmpresa.service.AutobusService;
import com.virtualtravel.common.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
@RestController
public class AutobusController {
@Autowired
 AutobusService autobusService;
@Autowired
KafkaProducerConfig producerConfig;


    @GetMapping("/{id}")
    public AutobusOutputDto obtenerAutobusPorId(@PathVariable int id) {
        return autobusService.obtenerAutobusPorId(id);
    }

    @GetMapping("/plazas-disponibles")
    public Integer obtenerPlazasDisponibles(@RequestParam String destino, @RequestParam Date fecha, @RequestParam float hora) {
        return autobusService.obtenerPlazasDisponibles(destino, fecha, hora);
    }

    @PostMapping("/{id}/actualizar-plazas")
    public void actualizarPlazasDisponibles(@PathVariable int id) {
        autobusService.actualizarPlazasDisponibles(id);
    }

    @PostMapping("/reserva")
    public ResponseEntity<Reserva>confirmarReserva(@RequestBody Reserva reservaOutputDto){
        autobusService.confirmarReserva(reservaOutputDto);
        producerConfig.enviarAutobus();
        return ResponseEntity.ok().body(reservaOutputDto);
    }
  @PostMapping("/autobus")
  public void enviarBus (){
      producerConfig.enviarAutobus();
   }
}

