package com.geraldSara.tarea7dwesGeraldSara.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Planta;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorRegistrarPlanta {

	@Autowired
	ServiciosFactory factory;

	@GetMapping("/registrarplanta")
	public String registrarplanta(HttpSession session) {
		String usuario = (String) session.getAttribute("usuario");
		if (usuario == null) {

			return "redirect:/login";
		}
		return "registrarplanta";
	}

	@PostMapping("/registrarplanta")
	public String registrarPlanta(@RequestParam String codigo, @RequestParam String nombreComun,
			@RequestParam String nombreCientifico, Model model) {

		boolean valido = true;
		if (!factory.getComprobaciones().esCodigoValido(codigo)) {
			model.addAttribute("errorCodigo", "Código no válido");
			valido = false;
		}

		if (factory.getServiciosPlanta().existeCodigoPlanta(codigo)) {
			model.addAttribute("errorCodigo", "Código ya registrado");
			valido = false;
		}

		if (!factory.getComprobaciones().nombreValido(nombreComun)) {
			model.addAttribute("errorNombreComun",
					"Nombre comun incorrecto. No puede contener números o solo espacios");
			valido = false;
		}

		if (!factory.getComprobaciones().nombreValido(nombreCientifico)) {
			model.addAttribute("errorNombreCientifico",
					"Nombre científico incorrecto. No puede contener número o solo espacios");
			valido = false;
		}

		if (valido) {
			Planta p = new Planta(codigo.toUpperCase(), nombreComun, nombreCientifico);

			if (factory.getServiciosPlanta().añadirPlanta(p) != null) {
				model.addAttribute("mensajeC", "Planta registrada con éxito");
			} else {
				model.addAttribute("mensaje", "Error al registrar la nueva planta");
			}
		}

		return "registrarplanta";
	}

}
