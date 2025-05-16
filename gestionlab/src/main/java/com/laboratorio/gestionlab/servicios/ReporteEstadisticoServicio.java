package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.DTO.EstadisticasAreaDTO;

import java.util.List;

public interface ReporteEstadisticoServicio {
    public List<EstadisticasAreaDTO> obtenerEstadisticoOrdenadoEnsayos();

    public List<EstadisticasAreaDTO> obtenerEstadisticoOrdenadoExperimentos();

    public List<EstadisticasAreaDTO> obtenerEstadisticoOrdenadoPorInvestigador();
}
