package com.bosonit.BackWeb_TPV.repositories;

import com.bosonit.BackWeb_TPV.domain.Autobuss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface AutobusRepository extends JpaRepository<Autobuss, Integer> {


    @Query(value="SELECT a FROM Autobuss a WHERE a.destino = ?1 AND a.fecha = ?2 AND a.hora = ?3")
    Autobuss obtenerPlazasDisponibles(String destino, Date fecha, float hora);

    @Query("SELECT a FROM Autobuss a WHERE a.destino = ?1 AND YEAR(fecha)=?2 AND MONTH(fecha)=?3 AND DAY(fecha)=?4 AND a.hora = ?5")
    Autobuss obtenerAutobusPorDestinoFechaHora(String destino, int ano, int mes, int dia, float hora);

}
