package com.geraldSara.tarea7dwesGeraldSara.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Credenciales;
import com.geraldSara.tarea7dwesGeraldSara.repositorios.CredencialesRepository;

@Service
public class ServiciosCredenciales {

	@Autowired
	private CredencialesRepository credenRepo;

	@Autowired
	private Environment environment;

	public boolean login(String usuario, String contrasena) {

		if (usuario.equals(environment.getProperty("spring.security.user.name"))
				&& contrasena.equals(environment.getProperty("spring.security.user.password"))) {
			return true;
		} else {
			// Busca una credencial que coincida con usuario y contraseña
			Credenciales credencial = credenRepo.findByUsuarioAndPassword(usuario, contrasena);

			// Retorna true si la credencial es válida (no es null)
			return credencial != null;
		}

	}

	public boolean existeUsuario(String usuario) {
		return credenRepo.existsByUsuario(usuario);
	}

	public List<Credenciales> listaUsuario() {
		return credenRepo.findAllByOrderByUsuarioAsc();
	}

}
