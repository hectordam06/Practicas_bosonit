package com.bosonit.block11uploaddownloadfiles.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "fichero")
@Data
public class Fichero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date fechaSubida;

    private String categoria;
}