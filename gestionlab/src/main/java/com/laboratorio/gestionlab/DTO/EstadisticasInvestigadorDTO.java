package com.laboratorio.gestionlab.DTO;

import java.util.Map;

public class EstadisticasInvestigadorDTO {
    private String nombreInvestigador;
    private String areaFuerte;
    private double tasaExitoExperimentos;
    private double tasaExitoEnsayos;
    private Map<String, Integer> exitoPorArea;

    public EstadisticasInvestigadorDTO(String nombreInvestigador, String areaFuerte, double tasaExitoExperimentos, double tasaExitoEnsayos, Map<String, Integer> exitoPorArea) {
        this.nombreInvestigador = nombreInvestigador;
        this.areaFuerte = areaFuerte;
        this.tasaExitoExperimentos = tasaExitoExperimentos;
        this.tasaExitoEnsayos = tasaExitoEnsayos;
        this.exitoPorArea = exitoPorArea;
    }

    public Map<String, Integer> getExitoPorArea() {
        return exitoPorArea;
    }

    public void setExitoPorArea(Map<String, Integer> exitoPorArea) {
        this.exitoPorArea = exitoPorArea;
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
