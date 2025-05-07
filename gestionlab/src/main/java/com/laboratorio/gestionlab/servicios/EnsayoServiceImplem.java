package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.entidades.Ensayo;
import com.laboratorio.gestionlab.repositorios.EnsayoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnsayoServiceImplem implements EnsayoService{

    @Autowired
    private EnsayoRepositorio repositorio;

    @Override
    public Ensayo guardarEnsayo(Ensayo ensayo) {
        return repositorio.save(ensayo);
    }

    @Override
    public List<Ensayo> listarEnsayos() {
        return repositorio.findAll();
    }

    @Override
    public Ensayo obtenerEnsayoPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public void eliminarEnsayo(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public Ensayo EncontrarPorTitulo(String Titulo) {
        return repositorio.findByTitulo(Titulo);
    }

    @Override
    public List<Ensayo> listarEnsayosPorFecha(LocalDate fecha) {
        return repositorio.findByFecha(fecha);
    }

    @Override
    public boolean existeEnsayoPorTitulo(String Titulo) {
        return repositorio.existsByTitulo(Titulo);
    }

    @Override
    public List<Ensayo> listarEnsayosPorExperimentoId(Long idExperimento) {
        return repositorio.findByExperimentoId(idExperimento);
    }
}
