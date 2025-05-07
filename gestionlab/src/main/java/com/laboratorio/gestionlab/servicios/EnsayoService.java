package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.entidades.Ensayo;

import java.time.LocalDate;
import java.util.List;

public interface EnsayoService {
    public Ensayo guardarEnsayo(Ensayo ensayo);

    public List<Ensayo> listarEnsayos();

    public Ensayo obtenerEnsayoPorId(Long id);

    public void eliminarEnsayo(Long id);

    public Ensayo EncontrarPorTitulo(String Titulo);

    public List<Ensayo> listarEnsayosPorFecha(LocalDate fecha);

    public boolean existeEnsayoPorTitulo(String Titulo);

    public List<Ensayo> listarEnsayosPorExperimentoId(Long idExperimento);



}
