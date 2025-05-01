package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.entidades.Observacion;

import java.util.List;

public interface ObservacionService {
    public Observacion guardarObservacion(Observacion observacion);

    public Observacion obtenerObservacionPorId(Long id);

    public void eliminarObservacion(Long id);

    public List<Observacion> obtenerTodasLasObservaciones();



}
