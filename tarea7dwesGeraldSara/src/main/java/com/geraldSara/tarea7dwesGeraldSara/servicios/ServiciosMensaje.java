package com.geraldSara.tarea7dwesGeraldSara.servicios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Ejemplar;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Mensaje;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Persona;
import com.geraldSara.tarea7dwesGeraldSara.repositorios.MensajeRepository;

@Service
public class ServiciosMensaje {

	@Autowired
	private MensajeRepository repoMensaje;

	@Autowired
	private ServiciosEjemplar servEjemplar;

	@Autowired
	private ServiciosPersona servPersona;

	public Mensaje crearMensaje(Mensaje m) {
		return repoMensaje.save(m);
	}

	public List<Mensaje> obtenerMensajesPorIdEjemplar(Long idejemplar) {
		return repoMensaje.findByEjemplarIdOrderByFechaHoraAsc(idejemplar);
	}

	public Mensaje crearMensaje(String mensaje, Long idEjemplar, String usuario) {
		Ejemplar e = servEjemplar.obtenerEjemplarporId(idEjemplar);
		Persona p = servPersona.obtenerPersonaPorUsuario(usuario);

		Mensaje nuevo = new Mensaje(LocalDateTime.now(), mensaje, e, p);

		return repoMensaje.saveAndFlush(nuevo);
	}

	public List<Mensaje> obtenerMensajesPorPersona(Long id) {
		return repoMensaje.obtenerMensajesPorPersona(id);
	}

	public List<Mensaje> obtenerMensajesPorFecha(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
		return repoMensaje.obtenerMensajesPorRangoDeFecha(fechaInicial, fechaFinal);
	}

	public List<Mensaje> obtenerMensajesPorPlanta(String codigo) {
		return repoMensaje.obtenerMensajesPorPlanta(codigo);
	}

}
