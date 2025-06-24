package com.laboratorio.gestionlab.controladores;

import com.laboratorio.gestionlab.DTO.EstadisticasAreaDTO;
import com.laboratorio.gestionlab.DTO.EstadisticasInvestigadorDTO;
import com.laboratorio.gestionlab.servicios.RecomendacionServicio;
import com.laboratorio.gestionlab.servicios.ReporteEstadisticoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;



@Controller
@RequestMapping("/estadisticas")
public class ControladorEstadisticas {

    @Autowired
    ReporteEstadisticoServicio servicio;

    @Autowired
    private RecomendacionServicio recomendacionServicio;

    @GetMapping("/area_ensayos")
    public String estadisticasAreaEnsayos(Model modelo) {
        List<EstadisticasAreaDTO> estadisticas = servicio.obtenerEstadisticoOrdenadoEnsayos();
        modelo.addAttribute("estadisticas", estadisticas);
        modelo.addAttribute("chartData", getChartDataEnsayosTotales());
        modelo.addAttribute("chartData2",getChartDatosPorcentajeExitosoEnsayo());

        return "estadisticas/area_ensayos";
    }

    @GetMapping("/area_experimentos")
    public String estadisticasAreaExperimentos(Model modelo) {
        List<EstadisticasAreaDTO> estadisticas = servicio.obtenerEstadisticoOrdenadoExperimentos();
        modelo.addAttribute("estadisticas", estadisticas);
        modelo.addAttribute("chartData", getChartDataExperimentosTotales());
        modelo.addAttribute("chartData2",getChartDataPorcentajeExitosoExperimento());
        return "estadisticas/area_experimentos";
    }


    @GetMapping("/investigadores")
    public String estadisticasInvestigadores(Model modelo){
        List<EstadisticasInvestigadorDTO> estadisticas = servicio.obtenerEstadisticoOrdenadoPorInvestigador();
        modelo.addAttribute("estadisticas", estadisticas);
        return "estadisticas/investigadores";
    }

    @GetMapping("/recomendaciones")
    public String recomendacionesInvestigadores(Model modelo){
        List<String> recomendaciones = servicio.generarRecomendacionesParaInvestigadores();
        modelo.addAttribute("recomendaciones", recomendaciones);
        return "estadisticas/recomendaciones";
    }

    @GetMapping("/actualizar_recomendaciones")
    public String actualizarRecomendaciones(){
        recomendacionServicio.subirRecomendaciones();
        return "redirect:/reportes";
    }

    @GetMapping("")




    private List<List<Object>> getChartDataEnsayosTotales(){
        List<EstadisticasAreaDTO> estadisticas = servicio.obtenerEstadisticoOrdenadoEnsayos();
        List<List<Object>> chartData = new ArrayList<>();

        for (EstadisticasAreaDTO estadistica : estadisticas) {
            List<Object> PuntoData = new ArrayList<>();
            PuntoData.add(estadistica.getNombreArea());
            PuntoData.add(estadistica.getTotalEnsayos());
            chartData.add(PuntoData);
        }

        return chartData;
    }

    private List<List<Object>> getChartDatosPorcentajeExitosoEnsayo(){
        List<EstadisticasAreaDTO> estadisticas = servicio.obtenerEstadisticoOrdenadoEnsayos();

        List<List<Object>> charData = new ArrayList<>();

        for (EstadisticasAreaDTO estadistica : estadisticas) {
            List<Object> PuntoData = new ArrayList<>();
            PuntoData.add(estadistica.getNombreArea());
            PuntoData.add(estadistica.getTasaExitoEnsayos());
            charData.add(PuntoData);
        }

        return charData;
    }

    private List<List<Object>> getChartDataExperimentosTotales(){
        List<EstadisticasAreaDTO> estadisticas = servicio.obtenerEstadisticoOrdenadoExperimentos();
        List<List<Object>> chartData = new ArrayList<>();

        for (EstadisticasAreaDTO estadistica : estadisticas) {
            List<Object> PuntoData = new ArrayList<>();
            PuntoData.add(estadistica.getNombreArea());
            PuntoData.add(estadistica.getTotalExperimentos());
            chartData.add(PuntoData);
        }

        return chartData;
    }

    private List<List<Object>> getChartDataPorcentajeExitosoExperimento(){
        List<EstadisticasAreaDTO> estadisticas = servicio.obtenerEstadisticoOrdenadoExperimentos();

        List<List<Object>> charData = new ArrayList<>();

        for (EstadisticasAreaDTO estadistica : estadisticas) {
            List<Object> PuntoData = new ArrayList<>();
            PuntoData.add(estadistica.getNombreArea());
            PuntoData.add(estadistica.getTasaExitoExperimentos());
            charData.add(PuntoData);
        }

        return charData;
    }


}
