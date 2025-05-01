package com.laboratorio.gestionlab.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "observacion")
public class Observacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;

    private String autor;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name="ensayo_id")
    private Ensayo ensayo;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;


}
