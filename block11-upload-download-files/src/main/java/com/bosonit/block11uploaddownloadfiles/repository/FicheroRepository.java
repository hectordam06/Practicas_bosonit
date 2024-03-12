package com.bosonit.block11uploaddownloadfiles.repository;

import com.bosonit.block11uploaddownloadfiles.model.Fichero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FicheroRepository extends JpaRepository<Fichero, Long> {


    Optional<Fichero> findByName(String name);
    }
