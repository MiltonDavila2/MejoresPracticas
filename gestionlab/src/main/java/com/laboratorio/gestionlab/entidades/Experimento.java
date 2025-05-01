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

    public Experimento(String titulo, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, AreaCientifica areaCientifica, Investigador investigador) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.areaCientifica = areaCientifica;
        this.investigador = investigador;
    }

    public Experimento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

    public AreaCientifica getAreaCientifica() {
        return areaCientifica;
    }

    public void setAreaCientifica(AreaCientifica areaCientifica) {
        this.areaCientifica = areaCientifica;
    }

    public List<Ensayo> getEnsayos() {
        return ensayos;
    }

    public void setEnsayos(List<Ensayo> ensayos) {
        this.ensayos = ensayos;
    }
}
