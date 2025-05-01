package com.laboratorio.gestionlab.controladores;

import com.laboratorio.gestionlab.entidades.AreaCientifica;
import com.laboratorio.gestionlab.servicios.AreaCientificaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/area")
public class ControladorAreaAdmin {
    @Autowired
    private AreaCientificaServicio servicioArea;

    @GetMapping("/listar_area")
    public String listarAreaCientifica(Model model, Authentication authentication) {
        List<AreaCientifica> areas = servicioArea.listarAreasCientificas();
        model.addAttribute("areas", areas);
        String rol = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_ANONYMOUS");
        model.addAttribute("rol", rol);
        return "area/listar_area";
    }

    @GetMapping("/crear")
    public String crearAreaCientifica(Model model){
        model.addAttribute("area", new AreaCientifica());
        return "area/crear_area";
    }

    @PostMapping("/guardar")
    public String guardarAreaCientifica(@ModelAttribute("area") AreaCientifica areaCientifica) {

        if(!servicioArea.existeAreaCientificaporNombre(areaCientifica.getNombre())){
            servicioArea.guardarAreaCientifica(areaCientifica);
        }

        return "redirect:/area/listar_area";
    }

    @GetMapping("/editar/{id}")
    public String editarAreaCientifica(@PathVariable Long id, Model model) {
        AreaCientifica area = servicioArea.obtenerAreaCientificaPorId(id);
        if (area==null){
            return "redirect:/area/listar_area";
        }
        model.addAttribute("area", area);
        return "area/editar_area";
    }

    @PostMapping("/actualizar")
    public String actualizarAreaCientifica(@ModelAttribute("area") AreaCientifica areaCientifica){
        AreaCientifica areaCientifica1 = servicioArea.obtenerAreaCientificaPorId(areaCientifica.getId());
        if(areaCientifica1==null){
            return "redirect:/area/listar_area";
        }

        areaCientifica1.setNombre(areaCientifica.getNombre());

        servicioArea.guardarAreaCientifica(areaCientifica1);
        return "redirect:/area/listar_area";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAreaCientifica(@PathVariable Long id) {
        AreaCientifica area = servicioArea.obtenerAreaCientificaPorId(id);
        if (area != null) {
            servicioArea.eliminarAreaCientificaPorId(id);
        }
        return "redirect:/area/listar_area";
    }





}
