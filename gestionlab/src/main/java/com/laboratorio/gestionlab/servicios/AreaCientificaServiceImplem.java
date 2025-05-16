package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.entidades.AreaCientifica;
import com.laboratorio.gestionlab.entidades.Experimento;
import com.laboratorio.gestionlab.repositorios.AreaCientificaRepositorio;
import com.laboratorio.gestionlab.repositorios.ExperimentoRepositorio;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaCientificaServiceImplem implements AreaCientificaServicio{

    @Autowired
    private AreaCientificaRepositorio repositorio;

    @Autowired
    private ExperimentoRepositorio experimentoRepositorio;

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

    @Transactional(readOnly = true)
    @Override
    public List<Experimento> listarExperimentosPorAreaCientifica(Long idAreaCientifica) {
        AreaCientifica area = repositorio.findById(idAreaCientifica).orElse(null);
        if (area == null) {
            return null;
        }
        return experimentoRepositorio.findByAreaCientifica(area);
    }
}
