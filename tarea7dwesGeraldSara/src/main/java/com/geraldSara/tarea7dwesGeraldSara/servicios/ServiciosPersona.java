package com.geraldSara.tarea7dwesGeraldSara.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Persona;
import com.geraldSara.tarea7dwesGeraldSara.repositorios.CredencialesRepository;
import com.geraldSara.tarea7dwesGeraldSara.repositorios.PersonaRepository;

@Service
public class ServiciosPersona {

	@Autowired
	private PersonaRepository repoPersona;

	@Autowired
	private CredencialesRepository repoCredenciales;
	

	public boolean existeEmail(String email) {
		return repoPersona.existsByEmail(email);
	}

	public Persona obtenerPersonaPorUsuario(String usuario) {
		return repoCredenciales.findPersonaByUsuario(usuario);
	}

	public Persona obtenerPersonaPorId(Long id) {
		return repoPersona.findPersonaById(id);
	}

}
