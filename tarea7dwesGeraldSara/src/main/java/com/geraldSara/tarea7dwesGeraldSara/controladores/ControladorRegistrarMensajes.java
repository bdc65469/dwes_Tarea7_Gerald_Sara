package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Ejemplar;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Mensaje;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Persona;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorRegistrarMensajes {

	@Autowired
	ServiciosFactory factory;

	@GetMapping("/registrarmensajes")
	public String listadoEjemplares(Model model, HttpSession session) {

		String usuario = (String) session.getAttribute("usuario");
		if (usuario == null) {

			return "redirect:/login";
		}

		List<Ejemplar> ejemplares = factory.getServiciosEjemplar().listadoEjemplares();
		model.addAttribute("ejemplares", ejemplares);
		return "registrarmensajes";
	}

	@PostMapping("/registrarmensajes")
	public String editarPlanta(@RequestParam String mensaje, @RequestParam("ejemplar") Long id, HttpSession session,
			Model model) {
		String usuario = (String) session.getAttribute("usuario");

		if (usuario != null) {

			Persona p = factory.getServiciosPersona().obtenerPersonaPorUsuario(usuario);
			Ejemplar e = factory.getServiciosEjemplar().obtenerEjemplarporId(id);

			if (p != null && e != null) {
				Mensaje m = new Mensaje(LocalDateTime.now(), mensaje, e, p);

				if (factory.getServiciosMensaje().crearMensaje(m) != null) {
					model.addAttribute("mensajeC", "Nuevo mensaje registrado con éxito");
				} else {
					model.addAttribute("mensaje", "Error al crear el mensaje");
				}
			} else {
				model.addAttribute("mensaje", "Error al crear el mensaje");
			}

		} else {
			model.addAttribute("mensaje",
					"Error al obtener el usuario conectado. No se pudo registrar el nuevo ejemplar");
		}

		listadoEjemplares(model, session);
		return "registrarmensajes";
	}

}
