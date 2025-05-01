package com.laboratorio.gestionlab.entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class AreaCientifica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "areaCientifica")
    private List<Experimento> experimentos;
}
