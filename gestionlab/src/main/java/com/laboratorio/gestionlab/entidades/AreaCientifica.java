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

    public AreaCientifica() {
    }

    public AreaCientifica(String nombre, List<Experimento> experimentos) {
        this.nombre = nombre;
        this.experimentos = experimentos;
    }

    public AreaCientifica(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Experimento> getExperimentos() {
        return experimentos;
    }

    public void setExperimentos(List<Experimento> experimentos) {
        this.experimentos = experimentos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
