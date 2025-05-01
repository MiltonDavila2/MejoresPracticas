package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.entidades.AreaCientifica;

import java.util.List;

public interface AreaCientificaServicio {
    public AreaCientifica guardarAreaCientifica(AreaCientifica areaCientifica);
    public List<AreaCientifica> listarAreasCientificas();
    public AreaCientifica obtenerAreaCientificaPorId(Long id);
    public AreaCientifica obtenerAreaCientificaPorNombre(String nombre);
    public void eliminarAreaCientificaPorId(Long id);
    public boolean existeAreaCientificaporNombre(String nombre);
}
