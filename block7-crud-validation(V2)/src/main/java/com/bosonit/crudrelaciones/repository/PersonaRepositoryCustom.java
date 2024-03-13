package com.bosonit.crudrelaciones.repository;

import com.bosonit.crudrelaciones.domain.Persona;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface PersonaRepositoryCustom {
    Page<Persona> searchWithPagination(String user, String name, String surname, Date created_date, Date termination_date, String orderBy, int page, int size);
}
