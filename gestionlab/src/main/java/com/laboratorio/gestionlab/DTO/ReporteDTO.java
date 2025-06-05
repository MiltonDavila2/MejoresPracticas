package com.laboratorio.gestionlab.DTO;

import com.laboratorio.gestionlab.entidades.Investigador;

public class ReporteDTO {
    private String Reporte;
    private Investigador investigador;

    public ReporteDTO(String reporte, Investigador investigador) {
        Reporte = reporte;
        this.investigador = investigador;
    }


    public String getReporte() {
        return Reporte;
    }

    public void setReporte(String reporte) {
        Reporte = reporte;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }
}
