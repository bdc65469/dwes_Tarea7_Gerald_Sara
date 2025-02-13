package com.geraldSara.tarea6dwesGeraldSara.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geraldSara.tarea6dwesGeraldSara.modelo.Persona;
import com.geraldSara.tarea6dwesGeraldSara.modelo.Planta;
import com.geraldSara.tarea6dwesGeraldSara.servicios.ServiciosFactory;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorCrearEjemplar {

	@Autowired
	ServiciosFactory factory;

	@GetMapping("/crearejemplar")
	public String listadoPlantas(Model model, HttpSession session) {

		String usuario = (String) session.getAttribute("usuario");
		if (usuario == null) {

			return "redirect:/login";
		}
		// Llama al servicio para obtener la lista de plantas ordenada por nombre común
		List<Planta> plantas = factory.getServiciosPlanta().listaPlantas();

		// Añade la lista de plantas al modelo
		model.addAttribute("plantas", plantas);

		// Retorna la vista 'crearejemplar' (crearejemplar.html)
		return "crearejemplar";
	}

	@PostMapping("/crearejemplar")
	public String crearEjemplar(@RequestParam("id") Long id, Model model, HttpSession session) {

		String usuario = (String) session.getAttribute("usuario");

		if (usuario != null) {
			// El usuario está presente en la sesión
			Persona p = factory.getServiciosPersona().obtenerPersonaPorUsuario(usuario);
			if (factory.getServiciosEjemplar().crearEjemplarYMensaje(id, p) != null) {
				model.addAttribute("mensajeC", "Nuevo ejemplar registrado con éxito");
			} else {
				model.addAttribute("mensaje", "Error al actualizar los datos de la planta");
			}
		} else {
			// El usuario no está presente en la sesión
			model.addAttribute("mensaje",
					"Error al obtener el usuario conectado. No se pudo registrar el nuevo ejemplar");
		}

		listadoPlantas(model, session);
		return "crearejemplar";
	}

}
