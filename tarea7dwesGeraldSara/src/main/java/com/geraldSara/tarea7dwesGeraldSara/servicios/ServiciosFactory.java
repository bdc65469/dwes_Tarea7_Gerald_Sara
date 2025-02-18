package com.geraldSara.tarea7dwesGeraldSara.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiciosFactory {

	@Autowired
	private ServiciosCredenciales serviCre;
	
	@Autowired
	private ServiciosCliente serviCli;

	@Autowired
	private ServiciosEjemplar serviEjem;

	@Autowired
	private ServiciosMensaje serviMen;

	@Autowired
	private ServiciosPersona serviPer;

	@Autowired
	private ServiciosPlanta serviPlan;

	@Autowired
	private Comprobaciones comprobaciones;

	public ServiciosEjemplar getServiciosEjemplar() {
		return serviEjem;
	}

	public ServiciosPersona getServiciosPersona() {
		return serviPer;
	}

	public ServiciosPlanta getServiciosPlanta() {
		return serviPlan;
	}

	public ServiciosMensaje getServiciosMensaje() {
		return serviMen;
	}

	public ServiciosCredenciales getServiciosCredenciales() {
		return serviCre;
	}
	
	public ServiciosCliente getServiciosClientes() {
		return serviCli;
	}

	public Comprobaciones getComprobaciones() {
		return comprobaciones;
	}
}
