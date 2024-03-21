package com.bosonit.block7crud.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter

@NoArgsConstructor
@Table(name = "PERSONA")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String poblacion;
}
