package com.laboratorio.gestionlab.repositorios;

import com.laboratorio.gestionlab.entidades.Experimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperimentoRepositorio extends JpaRepository<Experimento,Long> {
    Experimento findByNombre(String nombre);

}
