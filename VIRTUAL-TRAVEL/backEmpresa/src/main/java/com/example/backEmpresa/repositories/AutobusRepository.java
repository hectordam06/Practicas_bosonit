package com.example.backEmpresa.repositories;

import com.example.backEmpresa.domain.Autobus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.Optional;

public interface AutobusRepository extends JpaRepository<Autobus, Integer> {


    @Query(value="SELECT capacidad FROM Autobus  WHERE destino = ?1 AND fecha = ?2 AND hora = ?3")
    Optional<Autobus> obtenerPlazasDisponibles(String destino, Date fecha, float hora);

    @Query("SELECT a FROM Autobus a WHERE a.destino = ?1 AND YEAR(fecha)=?2 AND MONTH(fecha)=?3 AND DAY(fecha)=?4 AND a.hora = ?5")
    Autobus obtenerAutobusPorDestinoFechaHora(String destino, int ano,int mes,int dia, float hora);

}
