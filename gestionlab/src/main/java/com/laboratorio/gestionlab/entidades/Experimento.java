package com.laboratorio.gestionlab.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "experimento")
public class Experimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descripcion;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name="investigador_id")
    private Investigador investigador;

    @ManyToOne
    @JoinColumn(name="area_cientifica_id")
    private AreaCientifica areaCientifica;

    @OneToMany(mappedBy = "experimento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ensayo> ensayos;
}
