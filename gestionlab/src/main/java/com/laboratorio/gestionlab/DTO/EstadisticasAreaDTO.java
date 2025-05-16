package com.laboratorio.gestionlab.DTO;

public class EstadisticasAreaDTO {
    private String nombreArea;
    private int totalEnsayos;
    private int exitososEnsayos;
    private int fallidosEnsayos;
    private double tasaExitoEnsayos;

    private int totalExperimentos;
    private int exitososExperimentos;
    private int fallidosExperimentos;
    private double tasaExitoExperimentos;

    public EstadisticasAreaDTO(int totalEnsayos, String nombreArea, int fallidosEnsayos, int exitososEnsayos, double tasaExitoEnsayos, int totalExperimentos, int exitososExperimentos, int fallidosExperimentos, double tasaExitoExperimentos) {
        this.totalEnsayos = totalEnsayos;
        this.nombreArea = nombreArea;
        this.fallidosEnsayos = fallidosEnsayos;
        this.exitososEnsayos = exitososEnsayos;
        this.tasaExitoEnsayos = tasaExitoEnsayos;
        this.totalExperimentos = totalExperimentos;
        this.exitososExperimentos = exitososExperimentos;
        this.fallidosExperimentos = fallidosExperimentos;
        this.tasaExitoExperimentos = tasaExitoExperimentos;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public int getTotalEnsayos() {
        return totalEnsayos;
    }

    public void setTotalEnsayos(int totalEnsayos) {
        this.totalEnsayos = totalEnsayos;
    }

    public int getExitososEnsayos() {
        return exitososEnsayos;
    }

    public void setExitososEnsayos(int exitososEnsayos) {
        this.exitososEnsayos = exitososEnsayos;
    }

    public int getFallidosEnsayos() {
        return fallidosEnsayos;
    }

    public void setFallidosEnsayos(int fallidosEnsayos) {
        this.fallidosEnsayos = fallidosEnsayos;
    }

    public double getTasaExitoEnsayos() {
        return tasaExitoEnsayos;
    }

    public void setTasaExitoEnsayos(double tasaExitoEnsayos) {
        this.tasaExitoEnsayos = tasaExitoEnsayos;
    }

    public int getTotalExperimentos() {
        return totalExperimentos;
    }

    public void setTotalExperimentos(int totalExperimentos) {
        this.totalExperimentos = totalExperimentos;
    }

    public int getFallidosExperimentos() {
        return fallidosExperimentos;
    }

    public void setFallidosExperimentos(int fallidosExperimentos) {
        this.fallidosExperimentos = fallidosExperimentos;
    }

    public int getExitososExperimentos() {
        return exitososExperimentos;
    }

    public void setExitososExperimentos(int exitososExperimentos) {
        this.exitososExperimentos = exitososExperimentos;
    }

    public double getTasaExitoExperimentos() {
        return tasaExitoExperimentos;
    }

    public void setTasaExitoExperimentos(double tasaExitoExperimentos) {
        this.tasaExitoExperimentos = tasaExitoExperimentos;
    }
}
