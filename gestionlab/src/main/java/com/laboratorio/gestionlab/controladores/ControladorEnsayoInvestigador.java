package com.laboratorio.gestionlab.controladores;

import com.laboratorio.gestionlab.entidades.Ensayo;
import com.laboratorio.gestionlab.servicios.EnsayoService;
import com.laboratorio.gestionlab.servicios.ExperimentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/ensayo")
public class ControladorEnsayoInvestigador {
    @Autowired
    private EnsayoService ensayoService;

    @Autowired
    private ExperimentoServicio experimentoServicio;

    @GetMapping("/listar_ensayo/{experimentoId}")
    public String listarEnsayosPorExperimento(@PathVariable Long experimentoId, Model modelo) {
        modelo.addAttribute("ensayos", ensayoService.listarEnsayosPorExperimentoId(experimentoId));
        modelo.addAttribute("experimento", experimentoServicio.obtenerExperimentoPorId(experimentoId));
        return "ensayo/listar_ensayo";
    }

    @GetMapping("/crear/{experimentoId}")
    public String mostrarFormularioCrearEnsayo(@PathVariable Long experimentoId, Model modelo) {
        modelo.addAttribute("ensayo", new Ensayo());
        modelo.addAttribute("experimento", experimentoServicio.obtenerExperimentoPorId(experimentoId));
        return "ensayo/crear_ensayo";
    }


    @PostMapping("/guardar/{experimentoId}")
    public String guardarEnsayo(@PathVariable Long experimentoId, Ensayo ensayo) {

        if(experimentoServicio.obtenerExperimentoPorId(experimentoId) != null) {
            ensayo.setExperimento(experimentoServicio.obtenerExperimentoPorId(experimentoId));
            ensayo.setFecha(LocalDate.now());
            ensayoService.guardarEnsayo(ensayo);
        }

        return "redirect:/ensayo/listar_ensayo/" + experimentoId;
    }



}
