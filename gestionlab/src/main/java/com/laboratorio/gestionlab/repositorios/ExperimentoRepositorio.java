package com.laboratorio.gestionlab.repositorios;

import com.laboratorio.gestionlab.entidades.Experimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExperimentoRepositorio extends JpaRepository<Experimento,Long> {


    Experimento findByTitulo(String titulo);

    @Query("SELECT e FROM Experimento e WHERE e.fechaInicio = :fechaInicio AND e.fechaFin = :fechaFin")
    List<Experimento> findByFechasExactas(@Param("fechaInicio") LocalDate fechaInicio,
                                          @Param("fechaFin") LocalDate fechaFin);

    boolean existsByTitulo(String titulo);
}
