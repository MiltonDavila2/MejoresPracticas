package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.entidades.Experimento;

import java.time.LocalDate;
import java.util.List;

public interface ExperimentoServicio {

    public Experimento guardarExperimento(Experimento experimento);

    public List<Experimento> listarExperimentos();

    public Experimento obtenerExperimentoPorId(Long id);

    public void eliminarExperimento(Long id);

    public List<Experimento> buscarPorRangoFechas(LocalDate fechaMin, LocalDate fechaMax);

    public Experimento obtenerExperimentoPorTitulo(String titulo);

    public boolean ExisteExperimentoPorTitulo(String titulo);


}
