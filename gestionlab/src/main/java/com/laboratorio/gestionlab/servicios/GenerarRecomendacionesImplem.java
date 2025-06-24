package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.DTO.RecomendacionInvestigador;
import com.laboratorio.gestionlab.entidades.Investigador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenerarRecomendacionesImplem implements  GenerarRecomendaciones {

    @Autowired
    private InvestigadorServicio investigadorServicio;

    @Autowired
    private ReporteEstadisticoServicio reporteEstadisticoServicio;

    @Override
    public List<RecomendacionInvestigador> generar() {
        List<Investigador> investigadores = investigadorServicio.ListarInvestigadores();
        List<String> recomendacionesTexto = reporteEstadisticoServicio.generarRecomendacionesParaInvestigadores();

        List<RecomendacionInvestigador> recomendaciones = new ArrayList<>();
        for(int i=0;i<investigadores.size();i++){
            if(i<recomendacionesTexto.size()){
                recomendaciones.add(
                        new RecomendacionInvestigador(
                                recomendacionesTexto.get(i),
                                investigadores.get(i)

                        )
                );
            }
        }

        return  recomendaciones;
    }
}
