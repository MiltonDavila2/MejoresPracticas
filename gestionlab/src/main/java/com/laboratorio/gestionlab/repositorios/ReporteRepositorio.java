package com.laboratorio.gestionlab.repositorios;

import com.laboratorio.gestionlab.entidades.Investigador;
import com.laboratorio.gestionlab.entidades.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReporteRepositorio extends JpaRepository<Reporte, Long> {
    boolean existsByTextoAndInvestigador(String texto, Investigador investigador);

}
