package com.laboratorio.gestionlab.servicios;

import com.laboratorio.gestionlab.entidades.Rol;
import com.laboratorio.gestionlab.entidades.Usuario;
import com.laboratorio.gestionlab.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UsuarioServicioImplem implements UsuarioServicio{

    @Autowired
    private UsuarioRepositorio repositorio;

    @Override
    public List<Usuario> listarUsuarios() {
        return repositorio.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return repositorio.save(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminarUsuario(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public boolean estaElUsuario(String username) {
        Usuario usuario = repositorio.findByUsername(username);
        return usuario != null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repositorio.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return new User(usuario.getUsername(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRol()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Rol rol) {
        System.out.println("Rol asignado: " + rol.getNombre());
        return List.of(new SimpleGrantedAuthority(rol.getNombre()));
    }
}
