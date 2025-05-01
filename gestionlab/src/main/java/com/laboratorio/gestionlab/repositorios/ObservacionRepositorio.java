package com.laboratorio.gestionlab.repositorios;

import com.laboratorio.gestionlab.entidades.Observacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservacionRepositorio extends JpaRepository<Observacion,Long> {

}
