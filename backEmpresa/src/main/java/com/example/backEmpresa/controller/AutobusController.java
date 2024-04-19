package com.bosonit.BackWeb_TPV.controller;

import com.bosonit.BackWeb_TPV.controller.dto.AutobusOutputDto;
import com.bosonit.BackWeb_TPV.service.AutobusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class AutobusController {

    private AutobusService autobusService;

    @Autowired
    public AutobusController(AutobusService autobusService) {
        this.autobusService = autobusService;
    }

    @GetMapping("/{id}")
    public AutobusOutputDto obtenerAutobusPorId(@PathVariable int id) {
        return autobusService.obtenerAutobusPorId(id);
    }

    @GetMapping("/plazas-disponibles")
    public Long obtenerPlazasDisponibles(@RequestParam String destino, @RequestParam Date fecha, @RequestParam float hora) {
        return autobusService.obtenerPlazasDisponibles(destino, fecha, hora);
    }

    @PostMapping("/{id}/actualizar-plazas")
    public void actualizarPlazasDisponibles(@PathVariable int id) {
        autobusService.actualizarPlazasDisponibles(id);
    }
}

