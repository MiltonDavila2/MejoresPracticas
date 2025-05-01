package com.laboratorio.gestionlab.entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "investigador")
public class Investigador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedula;

    private String nombre_completa;

    private String especialidad;

    private String institucion;

    @OneToMany(mappedBy = "investigador", cascade = CascadeType.ALL)
    private List<Experimento> experimentos;
}
