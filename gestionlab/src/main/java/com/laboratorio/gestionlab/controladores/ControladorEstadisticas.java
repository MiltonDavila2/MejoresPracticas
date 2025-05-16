package com.laboratorio.gestionlab.controladores;

import com.laboratorio.gestionlab.DTO.EstadisticasAreaDTO;
import com.laboratorio.gestionlab.servicios.ReporteEstadisticoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/estadisticas")
public class ControladorEstadisticas {

    @Autowired
    ReporteEstadisticoServicio servicio;

    @GetMapping("/area_ensayos")
    public String estadisticasAreaEnsayos(Model modelo) {
        List<EstadisticasAreaDTO> estadisticas = servicio.obtenerEstadisticoOrdenadoEnsayos();
        modelo.addAttribute("estadisticas", estadisticas);
        return "estadisticas/area_ensayos";
    }

    @GetMapping("/area_experimentos")
    public String estadisticasAreaExperimentos(Model modelo) {
        List<EstadisticasAreaDTO> estadisticas = servicio.obtenerEstadisticoOrdenadoExperimentos();
        modelo.addAttribute("estadisticas", estadisticas);
        return "estadisticas/area_experimentos";
    }
}
