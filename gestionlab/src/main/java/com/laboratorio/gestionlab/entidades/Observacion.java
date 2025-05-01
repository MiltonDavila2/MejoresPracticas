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

    public Observacion() {
    }

    public Observacion(String comentario, String autor, LocalDate fecha, Ensayo ensayo, Usuario usuario) {
        this.comentario = comentario;
        this.autor = autor;
        this.fecha = fecha;
        this.ensayo = ensayo;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Ensayo getEnsayo() {
        return ensayo;
    }

    public void setEnsayo(Ensayo ensayo) {
        this.ensayo = ensayo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
