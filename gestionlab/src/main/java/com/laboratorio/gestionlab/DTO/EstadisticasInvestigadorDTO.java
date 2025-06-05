package com.laboratorio.gestionlab.DTO;

import java.util.Map;

public class EstadisticasInvestigadorDTO {
    private String nombreInvestigador;
    private String areaFuerte;
    private double tasaExitoExperimentos;
    private double tasaExitoEnsayos;
    private Map<String, Integer> experimentoPorArea;


    public EstadisticasInvestigadorDTO(String nombreInvestigador, String areaFuerte, double tasaExitoExperimentos, double tasaExitoEnsayos, Map<String, Integer> experimentoPorArea) {
        this.nombreInvestigador = nombreInvestigador;
        this.areaFuerte = areaFuerte;
        this.tasaExitoExperimentos = tasaExitoExperimentos;
        this.tasaExitoEnsayos = tasaExitoEnsayos;
        this.experimentoPorArea = experimentoPorArea;
    }

    public Map<String, Integer> getExperimentoPorArea() {
        return experimentoPorArea;
    }

    public void setExitoPorArea(Map<String, Integer> exitoPorArea) {
        this.experimentoPorArea = exitoPorArea;
    }

    public String getNombreInvestigador() {
        return nombreInvestigador;
    }

    public void setNombreInvestigador(String nombreInvestigador) {
        this.nombreInvestigador = nombreInvestigador;
    }

    public String getAreaFuerte() {
        return areaFuerte;
    }

    public void setAreaFuerte(String areaFuerte) {
        this.areaFuerte = areaFuerte;
    }

    public double getTasaExitoExperimentos() {
        return tasaExitoExperimentos;
    }

    public void setTasaExitoExperimentos(double tasaExitoExperimentos) {
        this.tasaExitoExperimentos = tasaExitoExperimentos;
    }

    public double getTasaExitoEnsayos() {
        return tasaExitoEnsayos;
    }

    public void setTasaExitoEnsayos(double tasaExitoEnsayos) {
        this.tasaExitoEnsayos = tasaExitoEnsayos;
    }
}
