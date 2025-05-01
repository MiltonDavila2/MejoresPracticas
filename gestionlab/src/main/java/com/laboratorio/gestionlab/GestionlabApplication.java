package com.laboratorio.gestionlab;

import com.laboratorio.gestionlab.entidades.Rol;
import com.laboratorio.gestionlab.entidades.Usuario;
import com.laboratorio.gestionlab.repositorios.RolRepositorio;
import com.laboratorio.gestionlab.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GestionlabApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GestionlabApplication.class, args);
	}

	@Autowired
	UsuarioServicio repositorio;

	@Autowired
	RolRepositorio repositoriorol;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		if (repositoriorol.findByNombre("ADMIN") == null) {
			repositoriorol.save(new Rol("ADMIN"));
		}

		Rol rolAdmin = repositoriorol.findByNombre("ADMIN");

		if(!repositorio.estaElUsuario("admin")){
			Usuario admin = new Usuario();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("1234"));

			admin.setRol(repositoriorol.findByNombre("ADMIN"));
			repositorio.guardarUsuario(admin);
		}
	}
}
