package com.laboratorio.gestionlab.DTO;

import com.laboratorio.gestionlab.entidades.Investigador;

public class RecomendacionInvestigador {
    private final  String Texto;
    private  final Investigador investigador;

    public RecomendacionInvestigador(String Texto, Investigador investigador){
        this.Texto=Texto;
        this.investigador=investigador;
    }

    public String getTexto() {
        return Texto;
    }

    public Investigador getInvestigador() {
        return investigador;
    }
}
