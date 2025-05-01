package com.laboratorio.gestionlab.repositorios;

import com.laboratorio.gestionlab.entidades.AreaCientifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaCientificaRepositorio extends JpaRepository<AreaCientifica,Long> {
    AreaCientifica findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
