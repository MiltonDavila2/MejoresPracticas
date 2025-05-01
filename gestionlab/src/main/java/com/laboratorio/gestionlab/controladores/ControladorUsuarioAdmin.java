package com.laboratorio.gestionlab.controladores;

import com.laboratorio.gestionlab.entidades.Rol;
import com.laboratorio.gestionlab.entidades.Usuario;
import com.laboratorio.gestionlab.repositorios.RolRepositorio;
import com.laboratorio.gestionlab.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class ControladorUsuarioAdmin {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/listar")
    public String ListarUsuarios(Model model, Authentication authentication){
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        String rol = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_ANONYMOUS");
        model.addAttribute("rol", rol);
        return "usuarios/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model){
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolRepositorio.findAll());
        return "usuarios/crear";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario){
        Rol rol = rolRepositorio.findById(usuario.getRol().getId()).orElse(null);

        if (usuarioServicio.estaElUsuario(usuario.getUsername())) {
            return "redirect:/usuarios/listar"; // Redirigir si el usuario ya existe
        }

        if (rol != null) {
            usuario.setRol(rol); // Establecemos un solo rol
        }


        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioServicio.guardarUsuario(usuario);
        return "redirect:/usuarios/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model){
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(id);
        if(usuario==null){
            return "redirect:/usuarios/listar";
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolRepositorio.findAll());
        return "usuarios/editar";
    }

    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        Usuario usuarioExistente = usuarioServicio.obtenerUsuarioPorId(usuario.getId());

        if (usuarioExistente == null) {
            return "redirect:/usuarios/listar";
        }

        usuarioExistente.setUsername(usuario.getUsername());


        if (!usuario.getPassword().isBlank()) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }


        Rol rol = rolRepositorio.findById(usuario.getRol().getId()).orElse(null);
        if (rol != null) {
            usuarioExistente.setRol(rol);
        }

        usuarioServicio.guardarUsuario(usuarioExistente);
        return "redirect:/usuarios/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id){
        usuarioServicio.eliminarUsuario(id);
        return "redirect:/usuarios/listar";
    }


}
