package com.geraldSara.tarea6dwesGeraldSara.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geraldSara.tarea6dwesGeraldSara.modelo.Ejemplar;
import com.geraldSara.tarea6dwesGeraldSara.modelo.Planta;
import com.geraldSara.tarea6dwesGeraldSara.servicios.ServiciosFactory;

@Controller
public class ControladorVerEjemplaresPlanta {

	@Autowired
	ServiciosFactory factory;

	@GetMapping("/verejemplaresplanta")
	public String listadoPlantas(Model model) {

		List<Planta> plantas = factory.getServiciosPlanta().listaPlantas();

		model.addAttribute("plantas", plantas);

		return "verejemplaresplanta";
	}

	@PostMapping("/ejemplaresplanta")
	public String listarEjemplaresPlanta(
			@RequestParam(name = "plantasSeleccionadas", required = false) List<Long> plantasSeleccionadas, Model model,
			RedirectAttributes redirectAttributes) {

		// Mapa donde la clave es la planta y el valor es la lista de ejemplares de esa planta
		Map<Planta, Set<Ejemplar>> ejemplaresPorPlanta = new HashMap<>();

		if (plantasSeleccionadas != null) {
			for (Long id : plantasSeleccionadas) {
				Planta planta = factory.getServiciosPlanta().obtenerPlantaporId(id);
				if (planta != null) {

					// Obtener los ejemplares de la planta y agregarlos al mapa
					Set<Ejemplar> ejemplares = factory.getServiciosEjemplar()
							.filtarEjemplaresPlanta(planta.getCodigo());
					ejemplaresPorPlanta.put(planta, ejemplares);
				}
			}
		}

		listadoPlantas(model);
		redirectAttributes.addFlashAttribute("ejemplaresPorPlanta", ejemplaresPorPlanta);

		return "redirect:/verejemplaresplanta#ejemplares";
	}

}
