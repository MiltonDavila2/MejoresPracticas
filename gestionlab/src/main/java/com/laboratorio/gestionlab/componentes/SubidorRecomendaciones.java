package com.laboratorio.gestionlab.componentes;

import com.laboratorio.gestionlab.DTO.RecomendacionInvestigador;
import com.laboratorio.gestionlab.entidades.Reporte;
import com.laboratorio.gestionlab.repositorios.ReporteRepositorio;
import com.laboratorio.gestionlab.servicios.GenerarRecomendaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubidorRecomendaciones {

    @Autowired
    private GenerarRecomendaciones generador;

    @Autowired
    private ReporteRepositorio reporteRepositorio;

    public void subir(){
        List<RecomendacionInvestigador> recomendaciones =  generador.generar();
        for(RecomendacionInvestigador r : recomendaciones){
            if(!reporteRepositorio.existsByTextoAndInvestigador(r.getTexto(),r.getInvestigador())){
                Reporte nuevo = new Reporte(r.getTexto(),r.getInvestigador());
                reporteRepositorio.save(nuevo);
            }
        }
    }
}
