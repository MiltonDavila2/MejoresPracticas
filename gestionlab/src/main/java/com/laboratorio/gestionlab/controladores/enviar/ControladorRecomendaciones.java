package com.laboratorio.gestionlab.controladores.enviar;

import com.laboratorio.gestionlab.DTO.RecomendacionInvestigador;
import com.laboratorio.gestionlab.DTO.RecomendacionesInvestigadorDTO;
import com.laboratorio.gestionlab.servicios.GenerarRecomendaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/enviar")
public class ControladorRecomendaciones {

    @Autowired
    private GenerarRecomendaciones generarRecomendaciones;

    @GetMapping("/generar")
    public List<RecomendacionesInvestigadorDTO> obtenerRecomendaciones(){
        List<RecomendacionInvestigador> recomendacionInvestigadors = generarRecomendaciones.generar();
        List<RecomendacionesInvestigadorDTO> recomendaciones = new ArrayList<>();
        for(RecomendacionInvestigador r: recomendacionInvestigadors){
            recomendaciones.add(new RecomendacionesInvestigadorDTO(r.getTexto(),r.getInvestigador().getNombre(),r.getInvestigador().getCedula()));
        }

        return recomendaciones;
    }
}
