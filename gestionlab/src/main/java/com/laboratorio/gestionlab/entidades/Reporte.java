package com.laboratorio.gestionlab.entidades;

import jakarta.persistence.*;

@Entity
@Table(name="reporte")
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String texto;



    @ManyToOne
    @JoinColumn(name="investigador_id")
    private Investigador investigador;


    public Reporte() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

    public Reporte(String texto, Investigador investigador) {
        this.texto = texto;
        this.investigador = investigador;
    }
}
