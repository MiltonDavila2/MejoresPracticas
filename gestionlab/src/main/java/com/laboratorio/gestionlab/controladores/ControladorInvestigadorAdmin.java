package com.laboratorio.gestionlab.controladores;

import com.laboratorio.gestionlab.entidades.Investigador;
import com.laboratorio.gestionlab.servicios.InvestigadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("investigador")
public class ControladorInvestigadorAdmin {
    @Autowired
    private InvestigadorServicio servicioInvestigadores;

    @GetMapping("/listar_investigadores")
    public String listarInvestigadores(Model modelo, Authentication authentication) {
        List<Investigador> investigadores = servicioInvestigadores.ListarInvestigadores();
        modelo.addAttribute("investigadores", investigadores);
        String rol = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_ANONYMOUS");
        modelo.addAttribute("rol", rol);

        return "investigador/listar_investigadores";
    }

    @GetMapping("/crear")
    public String crearInvestigador(Model modelo){
        modelo.addAttribute("investigador", new Investigador());
        return "investigador/crear_investigador";
    }

    @PostMapping("/guardar")
    public String guardarInvestigador(@ModelAttribute("investigador") Investigador investigador){
        if(!servicioInvestigadores.existeInvestigador(investigador.getCedula())){
            servicioInvestigadores.GuardarInvestigador(investigador);
        }

        return "redirect:/investigador/listar_investigadores";
    }

    @GetMapping("/editar/{id}")
    public String editarInvestigador(@PathVariable Long id, Model modelo){
        Investigador investigador = servicioInvestigadores.EncontrarInvestigadorPorID(id);
        if(investigador == null){
            return "redirect:/investigador/listar_investigadores";
        }
        modelo.addAttribute("investigador", investigador);
        return "investigador/editar_investigador";
    }

    @PostMapping("/actualizar")
    public String actualizarInvestigador(@ModelAttribute ("investigador") Investigador investigador){
        Investigador investigadorExistente = servicioInvestigadores.EncontrarInvestigadorPorID(investigador.getId());
        if(investigadorExistente != null){
            investigadorExistente.setNombre(investigador.getNombre());
            investigadorExistente.setCedula(investigador.getCedula());
            investigadorExistente.setEspecialidad(investigador.getEspecialidad());
            investigadorExistente.setInstitucion(investigador.getInstitucion());
            servicioInvestigadores.actualizarInvestigador(investigadorExistente);
        }
        return "redirect:/investigador/listar_investigadores";
    }

    @GetMapping("eliminar/{id}")
    public String eliminarInvestigador(@PathVariable Long id){
        Investigador investigador = servicioInvestigadores.EncontrarInvestigadorPorID(id);
        if(investigador != null){
            servicioInvestigadores.eliminarInvestigador(id);
        }
        return "redirect:/investigador/listar_investigadores";
    }


}
