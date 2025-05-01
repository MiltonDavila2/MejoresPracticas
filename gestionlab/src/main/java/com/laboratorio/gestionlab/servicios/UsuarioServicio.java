package com.laboratorio.gestionlab.servicios;


import com.laboratorio.gestionlab.entidades.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioServicio extends UserDetailsService {
    List<Usuario> listarUsuarios();
    Usuario guardarUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorId(Long id);
    void eliminarUsuario(Long id);
    boolean estaElUsuario(String username);
}
