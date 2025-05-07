package com.laboratorio.gestionlab.controladores;

import com.laboratorio.gestionlab.entidades.Experimento;
import com.laboratorio.gestionlab.servicios.AreaCientificaServicio;
import com.laboratorio.gestionlab.servicios.ExperimentoServicio;
import com.laboratorio.gestionlab.servicios.InvestigadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/experimento")
public class ControladorExperimentoInvestigador {

    @Autowired
    private AreaCientificaServicio AreaServicio;

    @Autowired
    private InvestigadorServicio InvestigadorServicio;

    @Autowired
    private ExperimentoServicio experimentoServicio;

    @GetMapping("/listar_experimentos")
    public String listarExperimentos(Model Modelo, Authentication authentication) {
        List<Experimento> experimentos =  experimentoServicio.listarExperimentos();
        Modelo.addAttribute("experimentos", experimentos);
        String rol = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_ANONYMOUS");
        Modelo.addAttribute("rol", rol);

        return "experimento/listar_experimentos";
    }

    @GetMapping("/crear_experimento")
    public String crearexperimento(Model modelo){
        Experimento experimento = new Experimento();
        experimento.setEstado("En proceso");
        modelo.addAttribute("experimento", experimento);
        modelo.addAttribute("areas", AreaServicio.listarAreasCientificas());
        modelo.addAttribute("investigadores", InvestigadorServicio.ListarInvestigadores());

        return "experimento/crear_experimento";
    }

    @PostMapping("/guardar")
    public String guardarExperimento(@ModelAttribute("experimento") Experimento experimento) {


        if(!experimentoServicio.ExisteExperimentoPorTitulo(experimento.getTitulo())){
            experimentoServicio.guardarExperimento(experimento);
        }
        return "redirect:/experimento/listar_experimentos";
    }

    @GetMapping("/editar/{id}")
    public String editarExperimento(@PathVariable Long id, Model modelo){
        Experimento experimento = experimentoServicio.obtenerExperimentoPorId(id);
        if(experimento==null){
            return "redirect:/experimento/listar_experimentos";
        }
        modelo.addAttribute("experimento", experimento);
        modelo.addAttribute("areas", AreaServicio.listarAreasCientificas());
        modelo.addAttribute("investigadores", InvestigadorServicio.ListarInvestigadores());
        return "experimento/editar_experimento";
    }

    @PostMapping("/actualizar")
    public String actualizarExperimento(@ModelAttribute("experimento") Experimento experimento) {
        Experimento experimentoExistente = experimentoServicio.obtenerExperimentoPorId(experimento.getId());
        if (experimentoExistente != null) {
            experimentoExistente.setTitulo(experimento.getTitulo());
            experimentoExistente.setDescripcion(experimento.getDescripcion());
            experimentoExistente.setEstado(experimento.getEstado());
            if (experimento.getFechaInicio() != null && !experimento.getFechaInicio().equals(experimentoExistente.getFechaInicio())) {
                experimentoExistente.setFechaInicio(experimento.getFechaInicio());
            }

            if (experimento.getFechaFin() != null && !experimento.getFechaFin().equals(experimentoExistente.getFechaFin())) {
                experimentoExistente.setFechaFin(experimento.getFechaFin());
            };
            experimentoExistente.setAreaCientifica(experimento.getAreaCientifica());
            experimentoExistente.setInvestigador(experimento.getInvestigador());
            experimentoServicio.guardarExperimento(experimentoExistente);
        }
        return "redirect:/experimento/listar_experimentos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarExperimento(@PathVariable Long id) {
        experimentoServicio.eliminarExperimento(id);
        return "redirect:/experimento/listar_experimentos";
    }





}
