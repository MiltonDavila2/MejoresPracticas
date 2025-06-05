package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.DTO.EstadisticasAreaDTO;
import com.laboratorio.gestionlab.DTO.EstadisticasInvestigadorDTO;

import java.util.List;

public interface ReporteEstadisticoServicio {
    public List<EstadisticasAreaDTO> obtenerEstadisticoOrdenadoEnsayos();

    public List<EstadisticasAreaDTO> obtenerEstadisticoOrdenadoExperimentos();

    public List<EstadisticasInvestigadorDTO> obtenerEstadisticoOrdenadoPorInvestigador();

    public List<String> generarRecomendacionesParaInvestigadores();

    public void subirRecomendaciones();
}
