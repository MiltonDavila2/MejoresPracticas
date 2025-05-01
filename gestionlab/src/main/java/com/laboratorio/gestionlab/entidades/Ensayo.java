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

    private LocalDate fecha;

    private String condiciones;

    private String resultado;

    private boolean exitoso;

    @ManyToOne
    @JoinColumn(name="experimento_id")
    private Experimento experimento;

    @OneToMany(mappedBy = "ensayo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Observacion> observaciones;
}
