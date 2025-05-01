package com.laboratorio.gestionlab.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ensayo")
public class Ensayo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private LocalDate fecha;

    private String condiciones;

    private String resultado;

    private boolean exitoso;

    @ManyToOne
    @JoinColumn(name="experimento_id")
    private Experimento experimento;

    @OneToMany(mappedBy = "ensayo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Observacion> observaciones;

    public Ensayo() {
    }

    public Ensayo(String titulo, LocalDate fecha, String condiciones, String resultado, boolean exitoso, Experimento experimento, List<Observacion> observaciones) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.condiciones = condiciones;
        this.resultado = resultado;
        this.exitoso = exitoso;
        this.experimento = experimento;
        this.observaciones = observaciones;
    }

    public Ensayo(String titulo, LocalDate fecha, String condiciones, String resultado, boolean exitoso, Experimento experimento) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.condiciones = condiciones;
        this.resultado = resultado;
        this.exitoso = exitoso;
        this.experimento = experimento;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }

    public Experimento getExperimento() {
        return experimento;
    }

    public void setExperimento(Experimento experimento) {
        this.experimento = experimento;
    }

    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }
}
