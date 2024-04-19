package com.bosonit.BackWeb_TPV.repositories;


import com.bosonit.BackWeb_TPV.domain.Autobus;
import com.bosonit.BackWeb_TPV.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Date;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query(value = "SELECT * FROM Reserva WHERE ciudad_Destino like ?1 ", nativeQuery = true)
    List<Reserva> findByCiudadDestinoAndFechaAndHora(String ciudadDestino, Date fecha, float hora);/*);*/


}

