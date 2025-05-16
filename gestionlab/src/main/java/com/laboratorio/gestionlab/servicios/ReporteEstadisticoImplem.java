package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.DTO.EstadisticasAreaDTO;
import com.laboratorio.gestionlab.entidades.AreaCientifica;
import com.laboratorio.gestionlab.entidades.Ensayo;
import com.laboratorio.gestionlab.entidades.Experimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteEstadisticoImplem implements ReporteEstadisticoServicio{

    @Autowired
    private AreaCientificaServicio areaCientificaServicio;

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
                    tasaExitoEnsayos= (double) (ensayosExitosos * 100) /totalEnsayos;
                }

                if(totalExperimentos!=0){
                    tasaExitoExperimentos= (double) (exitososExperimentos * 100) /totalExperimentos;
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
    public List<EstadisticasAreaDTO> obtenerEstadisticoOrdenadoPorInvestigador() {
        return List.of();
    }
}
