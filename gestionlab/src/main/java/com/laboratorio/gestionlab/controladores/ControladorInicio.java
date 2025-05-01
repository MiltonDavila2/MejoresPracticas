package com.laboratorio.gestionlab.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorInicio {

    @GetMapping("/login")
    public String IniciarSesion(){
        return "login";
    }

    @GetMapping("/error")
    public String Error(){
        return "error";
    }
}
