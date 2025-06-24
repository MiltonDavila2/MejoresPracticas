package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.componentes.SubidorRecomendaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecomendacionServicio {
    @Autowired
    private SubidorRecomendaciones subidorRecomendaciones;

    public void subirRecomendaciones(){
        subidorRecomendaciones.subir();
    }
}
