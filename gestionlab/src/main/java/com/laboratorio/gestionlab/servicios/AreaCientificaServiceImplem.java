package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.entidades.AreaCientifica;
import com.laboratorio.gestionlab.repositorios.AreaCientificaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaCientificaServiceImplem implements AreaCientificaServicio{

    @Autowired
    private AreaCientificaRepositorio repositorio;

    @Override
    public AreaCientifica guardarAreaCientifica(AreaCientifica areaCientifica) {
        return repositorio.save(areaCientifica);
    }

    @Override
    public List<AreaCientifica> listarAreasCientificas() {
        return repositorio.findAll();
    }

    @Override
    public AreaCientifica obtenerAreaCientificaPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public AreaCientifica obtenerAreaCientificaPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    @Override
    public void eliminarAreaCientificaPorId(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public boolean existeAreaCientificaporNombre(String nombre) {
        return repositorio.existsByNombre(nombre);
    }
}
