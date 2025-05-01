package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.entidades.Experimento;
import com.laboratorio.gestionlab.repositorios.ExperimentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExperimentoServicioImplem implements ExperimentoServicio{
    @Autowired
    private ExperimentoRepositorio repositorio;

    @Override
    public Experimento guardarExperimento(Experimento experimento) {
        return repositorio.save(experimento);
    }

    @Override
    public List<Experimento> listarExperimentos() {
        return repositorio.findAll();
    }

    @Override
    public Experimento obtenerExperimentoPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminarExperimento(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<Experimento> buscarPorRangoFechas(LocalDate fechaMin, LocalDate fechaMax) {
        return repositorio.findByFechasExactas(fechaMin, fechaMax);
    }

    @Override
    public Experimento obtenerExperimentoPorTitulo(String titulo) {
        return repositorio.findByTitulo(titulo);
    }

    @Override
    public boolean ExisteExperimentoPorTitulo(String titulo) {
        return repositorio.existsByTitulo(titulo);
    }
}
