package com.laboratorio.gestionlab.controladores;

import com.laboratorio.gestionlab.entidades.Investigador;
import com.laboratorio.gestionlab.entidades.Reporte;
import com.laboratorio.gestionlab.repositorios.ReporteRepositorio;
import com.laboratorio.gestionlab.servicios.InvestigadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reportes")
public class ControladorReporte {
    @Autowired
    private InvestigadorServicio investigadorServicio;

    @Autowired
    private ReporteRepositorio reporteRepositorio;

    @GetMapping
    public String mostrarFormulario(Model model) {
        List<Investigador> investigadores = investigadorServicio.ListarInvestigadores();
        model.addAttribute("investigadores", investigadores);
        return "reportes";
    }

    @PostMapping("/buscar")
    public String buscarReporte(@RequestParam("investigadorId") Long investigadorId, Model model) {
        List<Investigador> investigadores = investigadorServicio.ListarInvestigadores();
        model.addAttribute("investigadores", investigadores);

        List<Reporte> reportes = reporteRepositorio.findAll().stream()
                .filter(r -> r.getInvestigador() != null && r.getInvestigador().getId().equals(investigadorId))
                .toList();

        model.addAttribute("reportes", reportes);
        model.addAttribute("investigadorSeleccionado", investigadorId);
        return "reportes";
    }
}
