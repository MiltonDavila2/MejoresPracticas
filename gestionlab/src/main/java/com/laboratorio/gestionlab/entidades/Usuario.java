package com.laboratorio.gestionlab.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @OneToOne
    @JoinColumn(name="investigador_id", nullable = true)
    private Investigador investigador;



    public Usuario() {
    }

    public Usuario(String password, String username, Rol rol) {
        this.password = password;
        this.username = username;
        this.rol = rol;
    }

    public Usuario(String username, String password, Rol rol, Investigador investigador) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.investigador = investigador;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
