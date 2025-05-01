package com.laboratorio.gestionlab.repositorios;

import com.laboratorio.gestionlab.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long> {

    @Query(value = "SELECT * FROM rol WHERE nombre = :nombre", nativeQuery = true)
    Rol findByNombre(@Param("nombre") String nombre);
}
