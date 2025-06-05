package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.DTO.EstadisticasAreaDTO;
import com.laboratorio.gestionlab.DTO.EstadisticasInvestigadorDTO;
import com.laboratorio.gestionlab.entidades.*;
import com.laboratorio.gestionlab.repositorios.ReporteRepositorio;
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

    @Autowired
    private ReporteRepositorio reporteRepositorio;

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

                Map<String, Integer> experimentosPorArea = new HashMap<>();

                for (Experimento experimento : experimentos) {
                    if (!"En Proceso".equalsIgnoreCase(experimento.getEstado())) {
                        totalExperimentos++;
                        if ("Exitoso".equalsIgnoreCase(experimento.getEstado())) {
                            exitososExperimentos++;

                        }
                        String nombreArea = experimento.getAreaCientifica().getNombre();
                        experimentosPorArea.put(nombreArea, experimentosPorArea.getOrDefault(nombreArea, 0) + 1);
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

                String areaFuerte = experimentosPorArea.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse("No tiene datos");

                estadisticas.add(
                        new EstadisticasInvestigadorDTO(
                                investigador.getNombre(),
                                areaFuerte,
                                tasaExitoExperimentos,
                                tasaExitoEnsayos,
                                experimentosPorArea
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
        List<EstadisticasInvestigadorDTO> estadisticasInvestigador = obtenerEstadisticoOrdenadoPorInvestigador();

        if ((estadisticasInvestigador == null || estadisticasInvestigador.isEmpty()) || (estadisticasArea == null || estadisticasArea.isEmpty())) {
            recomendaciones.add("No hay investigadores o Areas para recomendación.");
            return recomendaciones;
        }

        Map<String, EstadisticasAreaDTO> mapaAreaDTO = estadisticasArea.stream()
                .collect(Collectors.toMap(EstadisticasAreaDTO::getNombreArea, area -> area));

        for(EstadisticasInvestigadorDTO investigador : estadisticasInvestigador){

            String areaFuerte = investigador.getAreaFuerte();
            Map<String, Integer> experimentosporArea = investigador.getExperimentoPorArea();

            boolean recomendacionDada = false;

            if(experimentosporArea == null || experimentosporArea.isEmpty()){
                recomendaciones.add("El investigador " + investigador.getNombreInvestigador() + " no tiene datos para recomendación.");
                continue;
            }

            double porcentajeExitoInvestigadorEnsayos = investigador.getTasaExitoEnsayos();
            double porcentajeExitoInvestigadorExperimentos = investigador.getTasaExitoExperimentos();



            if(porcentajeExitoInvestigadorExperimentos >= 50 && porcentajeExitoInvestigadorEnsayos >= 50){
                for(String otraArea: experimentosporArea.keySet()){
                    if(otraArea.equals(areaFuerte)) continue;

                    EstadisticasAreaDTO areaDTO = mapaAreaDTO.get(otraArea);

                    if(areaDTO!=null){
                        boolean necesitaMejorarExperimentos = areaDTO.getTasaExitoExperimentos() < 50;
                        boolean necesitaMejorarEnsayos = areaDTO.getTasaExitoEnsayos() < 50;

                        if(necesitaMejorarEnsayos || necesitaMejorarExperimentos){
                            String tipo = "";
                            if (necesitaMejorarEnsayos && necesitaMejorarExperimentos){
                                tipo="Ensayos y Experimentos";
                            }else if(necesitaMejorarEnsayos){
                                tipo="Ensayos";
                            }else{
                                tipo="Experimentos";
                            }

                            recomendaciones.add("Investigador " + investigador.getNombreInvestigador() +
                                    " tiene un buen desempeño en el área " + areaFuerte + " y buen desempeño en general." +
                                    " Se recomienda reasignarlo al área " + otraArea +
                                    " para apoyar en la mejora de los " + tipo.toLowerCase() + " del área " + otraArea + ".");

                            recomendacionDada = true;
                            break;
                        }
                    }
                }

            }

            if(!recomendacionDada){
                int numeroAreas = experimentosporArea.size();

                if(porcentajeExitoInvestigadorExperimentos >= 50 && porcentajeExitoInvestigadorEnsayos >= 50){
                    if(numeroAreas == 1){
                        recomendaciones.add("Investigador " + investigador.getNombreInvestigador() +
                                " tiene un buen desempeño en general y su mejor desempeño se encuentra en el área de " + areaFuerte + ".");
                    } else {
                        String peorArea = experimentosporArea.entrySet().stream()
                                .min(Map.Entry.comparingByValue())
                                .map(Map.Entry::getKey)
                                .orElse("Ninguna");

                        recomendaciones.add("El investigador " + investigador.getNombreInvestigador() +
                                " tiene un buen desempeño en general, destacándose especialmente en el área de " + areaFuerte +
                                ". Se le sugiere brindar apoyo en el área de " + peorArea + ", ya sea participando en experimentos o ensayos para fortalecer su experiencia en esa área.");
                    }

                }else {
                    String peorArea = experimentosporArea.entrySet().stream()
                            .min(Map.Entry.comparingByValue())
                            .map(Map.Entry::getKey)
                            .orElse("Error");

                    recomendaciones.add("Investigador " + investigador.getNombreInvestigador() +
                            " necesita mejorar sus ensayos o experimentos en general, específicamente en el área de " + peorArea + ".");
                }
            }

        }

        return recomendaciones;
    }

    @Override
    public void subirRecomendaciones() {
        List<Investigador> investigadores = investigadorServicio.ListarInvestigadores();
        List<String> recomendaciones = generarRecomendacionesParaInvestigadores();


        for (int i = 0; i < investigadores.size(); i++) {
            Investigador investigador = investigadores.get(i);


            String recomendacion = i < recomendaciones.size() ? recomendaciones.get(i) : null;

            if (recomendacion != null &&
                    !reporteRepositorio.existsByTextoAndInvestigador(recomendacion, investigador)) {
                Reporte reporte = new Reporte(recomendacion, investigador);
                reporteRepositorio.save(reporte);
            }

        }
    }


}
