package com.geraldSara.tarea7dwesGeraldSara.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Cliente;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Credenciales;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Persona;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Rol;
import com.geraldSara.tarea7dwesGeraldSara.repositorios.ClienteRepository;
import com.geraldSara.tarea7dwesGeraldSara.repositorios.CredencialesRepository;
import com.geraldSara.tarea7dwesGeraldSara.repositorios.PersonaRepository;

@Service
public class ServiciosCredenciales {

	@Autowired
	private CredencialesRepository credenRepo;
	
	@Autowired
	private PersonaRepository personaRepo;
	
	@Autowired
	private ClienteRepository cliRepo;

	@Autowired
	private Environment environment;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
	
	public Credenciales obtenerCreden(String usuario, String pass) {
		return credenRepo.findByUsuarioAndPassword(usuario, pass);
	}
	
	public Credenciales registrarCliente(String usuario, String password, Rol rol, Cliente c) {
		cliRepo.save(c);
        String passwordEncriptada = passwordEncoder.encode(password);
        Credenciales credencial = new Credenciales(usuario, passwordEncriptada, rol, c);
        return credenRepo.save(credencial);
    }
	
	public Credenciales registrarPersona(String usuario, String password, Rol rol, Persona p) {
		personaRepo.save(p);
        String passwordEncriptada = passwordEncoder.encode(password);
        Credenciales credencial = new Credenciales(usuario, passwordEncriptada, rol, p);
        return credenRepo.save(credencial);
    }

}
