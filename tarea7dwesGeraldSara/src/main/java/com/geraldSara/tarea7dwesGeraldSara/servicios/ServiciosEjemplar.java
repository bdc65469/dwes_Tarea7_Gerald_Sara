package com.geraldSara.tarea7dwesGeraldSara.servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Ejemplar;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Mensaje;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Persona;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Planta;
import com.geraldSara.tarea7dwesGeraldSara.repositorios.EjemplarRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ServiciosEjemplar {

	@Autowired
	private EjemplarRepository repoEjemplar;

	@Autowired
	private ServiciosPlanta servPlanta;

	@Transactional
	public Ejemplar crearEjemplarYMensaje(Long plantaId, Persona persona) {

		Planta planta = servPlanta.obtenerPlantaporId(plantaId);
		String nombreEjemplar = planta.getCodigo().toUpperCase() + repoEjemplar.ultimoIdEjemplarByPlanta(planta);
		Ejemplar ejemplar = new Ejemplar(nombreEjemplar, planta);

		Mensaje mensaje = new Mensaje(LocalDateTime.now(), "Nuevo ejemplar de " + planta.getNombrecomun() + " creado.",
				ejemplar, persona);

		ejemplar.getMensajes().add(mensaje);

		repoEjemplar.save(ejemplar);

		return ejemplar;
	}

	public Set<Ejemplar> filtarEjemplaresPlanta(String codigo) {
		return repoEjemplar.findByPlantaCodigo(codigo);
	}

	public List<Ejemplar> listadoEjemplares() {
		return repoEjemplar.findAll();
	}

	public Ejemplar obtenerEjemplarporId(Long id) {
		return repoEjemplar.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Ejemplar no encontrada con el ID: " + id));
	}
	
	public Integer numEjemplaresPorPlanta (Planta p) {
		return repoEjemplar.contarEjemplaresDisponiblesPorPlanta(p);
	}
	

	public List<Ejemplar> listaEjemplaresPorPlanta(Long id) {
		return repoEjemplar.findByPlanta_Id(id);	}
	
}
