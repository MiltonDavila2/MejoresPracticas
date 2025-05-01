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

    private String nombre;

    private String especialidad;

    private String institucion;

    @OneToMany(mappedBy = "investigador", cascade = CascadeType.ALL)
    private List<Experimento> experimentos;

    public Investigador() {
    }

    public Investigador(String cedula, String nombre, String especialidad, String institucion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.institucion = institucion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public List<Experimento> getExperimentos() {
        return experimentos;
    }

    public void setExperimentos(List<Experimento> experimentos) {
        this.experimentos = experimentos;
    }
}
