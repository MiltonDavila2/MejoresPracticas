package com.laboratorio.gestionlab.repositorios;

import com.laboratorio.gestionlab.entidades.Ensayo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnsayoRepositorio extends JpaRepository<Ensayo,Long> {

    Ensayo findByNombre(String nombre);
}
