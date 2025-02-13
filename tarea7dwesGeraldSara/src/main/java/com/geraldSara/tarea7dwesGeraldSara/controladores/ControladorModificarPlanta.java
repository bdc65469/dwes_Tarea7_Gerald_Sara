package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Planta;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;

@Controller
public class ControladorModificarPlanta {

	@Autowired
	ServiciosFactory factory;

	@GetMapping("/modificarplanta")
	public String modificarplanta(Model model) {

		List<Planta> plantas = factory.getServiciosPlanta().listaPlantas();
		model.addAttribute("plantas", plantas);
		return "modificarplanta";
	}

	@PostMapping("/modificarplanta")
	public String editarPlanta(@RequestParam String codigo, @RequestParam String nombreComun,
			@RequestParam String nombreCientifico, Model model) {

		boolean valido = true;
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
			Planta p = factory.getServiciosPlanta().obtenerPlantaPorCodigo(codigo);

			if (factory.getServiciosPlanta().actualizarPlanta(p, nombreComun, nombreCientifico) != null) {
				model.addAttribute("mensajeC", "Planta actualizada con éxito");
			} else {
				model.addAttribute("mensaje", "Error al actualizar los datos de la planta");
			}
		}

		modificarplanta(model);
		return "modificarplanta";
	}

}
