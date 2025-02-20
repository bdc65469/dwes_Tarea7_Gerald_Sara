package com.geraldSara.tarea7dwesGeraldSara.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Cliente;
import com.geraldSara.tarea7dwesGeraldSara.repositorios.ClienteRepository;
import com.geraldSara.tarea7dwesGeraldSara.repositorios.CredencialesRepository;

@Service
public class ServiciosCliente {
	
	@Autowired
	private ClienteRepository repoCliente;
	
	@Autowired
	private CredencialesRepository repoCredenciales;
	
	public Cliente obtenerClientePorUsuario(String usuario) {
		return repoCredenciales.findClienteByUsuario(usuario);
	}

	public Cliente obtenerClientePorId(Long id) {
		return repoCliente.findClienteById(id);
	}
	
	public boolean existeNif(String nif) {
		return repoCliente.existsByNif(nif);
	}

	public boolean existeEmail(String email) {
		// TODO Auto-generated method stub
		return repoCliente.existsByEmail(email);
	}


}
