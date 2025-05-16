package com.laboratorio.gestionlab.repositorios;

import com.laboratorio.gestionlab.entidades.Ensayo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EnsayoRepositorio extends JpaRepository<Ensayo,Long> {

    Ensayo findByTitulo(String titulo);
    List<Ensayo> findByFecha(LocalDate fecha);
    boolean existsByTitulo(String titulo);
    List<Ensayo> findByExperimentoId(Long id);

}
