package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.DTO.EstadisticasAreaDTO;
import com.laboratorio.gestionlab.DTO.EstadisticasInvestigadorDTO;
import com.laboratorio.gestionlab.entidades.AreaCientifica;
import com.laboratorio.gestionlab.entidades.Ensayo;
import com.laboratorio.gestionlab.entidades.Experimento;
import com.laboratorio.gestionlab.entidades.Investigador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReporteEstadisticoImplem implements ReporteEstadisticoServicio{

    @Autowired
    private AreaCientificaServicio areaCientificaServicio;

    @Autowired
    private InvestigadorServicio investigadorServicio;

    @Autowired
    private ExperimentoServicio experimentoServicio;

    @Autowired
    private EnsayoService ensayoService;

    @Override
    public List<EstadisticasAreaDTO> obtenerEstadisticoOrdenadoEnsayos() {
        List<EstadisticasAreaDTO> estadisticas = new ArrayList<>();

        for(AreaCientifica area : areaCientificaServicio.listarAreasCientificas()){
            List<Experimento> experimentos = areaCientificaServicio.listarExperimentosPorAreaCientifica(area.getId());

            if (experimentos!=null){
                int totalEnsayos = 0;
                int ensayosExitosos = 0;
                int ensayosFallidos = 0;

                int totalExperimentos = 0;
                int exitososExperimentos = 0;
                int fallidosExperimentos = 0;

                for(Experimento experimento : experimentos){
                    List<Ensayo> ensayos = ensayoService.listarEnsayosPorExperimentoId(experimento.getId());

                    for(Ensayo ensayo:ensayos){
                        totalEnsayos++;
                        if(ensayo.isExitoso()){
                            ensayosExitosos++;
                        }else{
                            ensayosFallidos++;
                        }
                    }

                    if(!"En Proceso".equalsIgnoreCase(experimento.getEstado())){
                        totalExperimentos++;
                        if("Exitoso".equalsIgnoreCase(experimento.getEstado())){
                            exitososExperimentos++;
                        }else{
                            fallidosExperimentos++;
                        }
                    }
                }

                double tasaExitoEnsayos = 0;
                double tasaExitoExperimentos = 0;

                if(totalEnsayos!=0){
                    tasaExitoEnsayos= BigDecimal.valueOf((double) (ensayosExitosos * 100) /totalEnsayos).setScale(2, RoundingMode.HALF_UP).doubleValue();
                }

                if(totalExperimentos!=0){
                    tasaExitoExperimentos= BigDecimal.valueOf((double) (exitososExperimentos * 100) /totalExperimentos).setScale(2, RoundingMode.HALF_UP).doubleValue();
                }


                estadisticas.add(new EstadisticasAreaDTO(
                        totalEnsayos,
                        area.getNombre(),
                        ensayosFallidos,
                        ensayosExitosos,
                        tasaExitoEnsayos,
                        totalExperimentos,
                        exitososExperimentos,
                        fallidosExperimentos,
                        tasaExitoExperimentos
                ));


            }
        }

        return estadisticas.stream()
                .sorted(Comparator.comparingDouble(EstadisticasAreaDTO::getTasaExitoEnsayos).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<EstadisticasAreaDTO> obtenerEstadisticoOrdenadoExperimentos() {
        List<EstadisticasAreaDTO> estadisticas = new ArrayList<>();

        for(AreaCientifica area : areaCientificaServicio.listarAreasCientificas()){
            List<Experimento> experimentos = areaCientificaServicio.listarExperimentosPorAreaCientifica(area.getId());

            if (experimentos!=null){
                int totalEnsayos = 0;
                int ensayosExitosos = 0;
                int ensayosFallidos = 0;

                int totalExperimentos = 0;
                int exitososExperimentos = 0;
                int fallidosExperimentos = 0;

                for(Experimento experimento : experimentos){
                    List<Ensayo> ensayos = ensayoService.listarEnsayosPorExperimentoId(experimento.getId());

                    for(Ensayo ensayo:ensayos){
                        totalEnsayos++;
                        if(ensayo.isExitoso()){
                            ensayosExitosos++;
                        }else{
                            ensayosFallidos++;
                        }
                    }

                    if(!"En Proceso".equalsIgnoreCase(experimento.getEstado())){
                        totalExperimentos++;
                        if("Exitoso".equalsIgnoreCase(experimento.getEstado())){
                            exitososExperimentos++;
                        }else{
                            fallidosExperimentos++;
                        }
                    }
                }

                double tasaExitoEnsayos = 0;
                double tasaExitoExperimentos = 0;

                if(totalEnsayos!=0){
                    tasaExitoEnsayos= BigDecimal.valueOf((double) (ensayosExitosos * 100) /totalEnsayos).setScale(2, RoundingMode.HALF_UP).doubleValue();
                }

                if(totalExperimentos!=0){
                    tasaExitoExperimentos= BigDecimal.valueOf((double) (exitososExperimentos * 100) /totalExperimentos).setScale(2, RoundingMode.HALF_UP).doubleValue();
                }


                estadisticas.add(new EstadisticasAreaDTO(
                        totalEnsayos,
                        area.getNombre(),
                        ensayosFallidos,
                        ensayosExitosos,
                        tasaExitoEnsayos,
                        totalExperimentos,
                        exitososExperimentos,
                        fallidosExperimentos,
                        tasaExitoExperimentos
                ));


            }
        }

        return estadisticas.stream()
                .sorted(Comparator.comparingDouble(EstadisticasAreaDTO::getTasaExitoExperimentos).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<EstadisticasInvestigadorDTO> obtenerEstadisticoOrdenadoPorInvestigador() {
        List<EstadisticasInvestigadorDTO> estadisticas = new ArrayList<>();

        List<Investigador> investigadores = investigadorServicio.ListarInvestigadores();

        for (Investigador investigador : investigadores) {
            List<Experimento> experimentos = investigadorServicio.listarExperimentosPorInvestigador(investigador.getId());

            if (experimentos != null) {
                int totalExperimentos = 0;
                int exitososExperimentos = 0;
                int totalEnsayos = 0;
                int exitososEnsayos = 0;

                Map<String, Integer> exitosPorArea = new HashMap<>();

                for (Experimento experimento : experimentos) {
                    if (!"En Proceso".equalsIgnoreCase(experimento.getEstado())) {
                        totalExperimentos++;
                        if ("Exitoso".equalsIgnoreCase(experimento.getEstado())) {
                            exitososExperimentos++;
                        }
                        String nombreArea = experimento.getAreaCientifica().getNombre();
                        exitosPorArea.put(nombreArea, exitosPorArea.getOrDefault(nombreArea, 0) + 1);
                    }

                    List<Ensayo> ensayos = ensayoService.listarEnsayosPorExperimentoId(experimento.getId());
                    for (Ensayo ensayo : ensayos) {
                        totalEnsayos++;
                        if (ensayo.isExitoso()) {
                            exitososEnsayos++;
                        }
                    }
                }

                double tasaExitoExperimentos = 0;
                double tasaExitoEnsayos = 0;

                if (totalExperimentos != 0) {
                    tasaExitoExperimentos = BigDecimal.valueOf((double) (exitososExperimentos * 100) / totalExperimentos).setScale(2, RoundingMode.HALF_UP).doubleValue();
                }

                if (totalEnsayos != 0) {
                    tasaExitoEnsayos = BigDecimal.valueOf((double) (exitososEnsayos * 100) / totalEnsayos).setScale(2, RoundingMode.HALF_UP).doubleValue();
                }

                String areaFuerte = exitosPorArea.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse("No tiene datos");

                estadisticas.add(
                        new EstadisticasInvestigadorDTO(
                                investigador.getNombre(),
                                areaFuerte,
                                tasaExitoExperimentos,
                                tasaExitoEnsayos,
                                exitosPorArea
                        )
                );


            }


        }


        return estadisticas;
    }
}
