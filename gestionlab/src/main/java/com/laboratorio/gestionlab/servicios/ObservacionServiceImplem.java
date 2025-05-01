package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.entidades.Observacion;
import com.laboratorio.gestionlab.repositorios.ObservacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObservacionServiceImplem implements ObservacionService{
    @Autowired
    private ObservacionRepositorio repositorio;

    @Override
    public Observacion guardarObservacion(Observacion observacion) {
        return repositorio.save(observacion);
    }

    @Override
    public Observacion obtenerObservacionPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminarObservacion(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<Observacion> obtenerTodasLasObservaciones() {
        return repositorio.findAll();
    }
}
