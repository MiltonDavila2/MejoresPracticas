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

    @Override
    public List<String> generarRecomendacionesParaInvestigadores() {
        List<String> recomendaciones = new ArrayList<>();
        List<EstadisticasAreaDTO> estadisticasArea = obtenerEstadisticoOrdenadoEnsayos();
        List<EstadisticasInvestigadorDTO> estadisticasInvestigador= obtenerEstadisticoOrdenadoPorInvestigador();

        Map<String, EstadisticasAreaDTO> mapaAreaDTO = estadisticasArea.stream()
                .collect(Collectors.toMap(EstadisticasAreaDTO::getNombreArea, area -> area));

        for(EstadisticasInvestigadorDTO investigador : estadisticasInvestigador){

            String areaFuerte = investigador.getAreaFuerte();
            Map<String, Integer> exitosPorArea = investigador.getExitoPorArea();

            int exitosEnAreaFuerteInvestigador = exitosPorArea.getOrDefault(areaFuerte, 0);
            int exitosTotalesEnArea= mapaAreaDTO.containsKey(areaFuerte)?mapaAreaDTO.get(areaFuerte).getExitososEnsayos():0;

            double porcentajeExitoAreaFuerte = exitosTotalesEnArea!=0?
                    (double) exitosEnAreaFuerteInvestigador/exitosTotalesEnArea * 100:0;

            double porcentajeExitoInvestigadorEnsayos = investigador.getTasaExitoEnsayos();
            double porcentajeExitoInvestigadorExperimentos = investigador.getTasaExitoExperimentos();


            if(porcentajeExitoAreaFuerte>=30 && porcentajeExitoInvestigadorExperimentos>=50 && porcentajeExitoInvestigadorEnsayos>=50){
                boolean reasignacion = false;
                for(String otraArea : exitosPorArea.keySet()){
                    if(otraArea.equals(areaFuerte)) continue;

                    EstadisticasAreaDTO areaDTO = mapaAreaDTO.get(otraArea);

                    if(areaDTO!=null){
                        boolean necesitaMejoraExperimentos = areaDTO.getTasaExitoExperimentos() < 50;
                        boolean necesitaMejoraEnsayos = areaDTO.getTasaExitoEnsayos() < 50;

                        if (necesitaMejoraEnsayos||necesitaMejoraExperimentos){
                            String tipo = necesitaMejoraEnsayos && necesitaMejoraExperimentos ? "Ensayos y Experimentos" :
                                    (necesitaMejoraEnsayos ? "Ensayos" : "Experimentos");

                            recomendaciones.add("Investigador "+ investigador.getNombreInvestigador()+
                                    " tiene un buen desempeño en el área "+ areaFuerte + " y buen desempeño en general"+
                                    ". Se recomienda reasignarlo a la área "+ otraArea +
                                    " para apoyar en la mejora de los "+ tipo.toLowerCase() +" del área  " + otraArea +".");

                            reasignacion=true;
                            break;
                        }
                    }
                }

                if(!reasignacion){
                    String peorArea = exitosPorArea.entrySet().stream()
                            .min(Map.Entry.comparingByValue())
                            .map(Map.Entry::getKey)
                            .orElse("Ninguna");

                    recomendaciones.add("Investigador "+ investigador.getNombreInvestigador()+
                            " tiene un buen desempeño en general y su mejor desempeño en el área de "+ areaFuerte +
                            " se le sugiere asistir en el area de "+ peorArea +".");
                }
            }else {

                String peorArea = exitosPorArea.entrySet().stream()
                        .min(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse("Ninguna");

                recomendaciones.add("Investigador " + investigador.getNombreInvestigador() +
                        " necesita mejorar sus ensayos o experimentos en general, especificamente en el área de " + peorArea + ".");

            }
        }
        return recomendaciones;
    }

}
