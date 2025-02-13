package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Ejemplar;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Mensaje;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorMensajesEjemplar {

	@Autowired
	ServiciosFactory factory;

	@GetMapping("/vermensajeejemplar")
	public String listadoEjemplares(Model model, HttpSession session) {

		String usuario = (String) session.getAttribute("usuario");
		if (usuario == null) {

			return "redirect:/login";
		}

		List<Ejemplar> ejemplares = factory.getServiciosEjemplar().listadoEjemplares();
		model.addAttribute("ejemplares", ejemplares);
		return "vermensajeejemplar";
	}

	@PostMapping("/vermensajeejemplar")
	public String crearEjemplar(@RequestParam("id") Long id, RedirectAttributes redirectAttributes, HttpSession session,
			Model model) {

		List<Mensaje> listadoMensajes = factory.getServiciosMensaje().obtenerMensajesPorIdEjemplar(id);

		// Uso de redirect:, los atributos agregados al model no se mantienen porque la
		// redirección hace una nueva solicitud HTTP y los datos originales no se
		// transfieren automáticamente
		redirectAttributes.addFlashAttribute("listadoMensajes", listadoMensajes);

		listadoEjemplares(model, session);
		return "redirect:/vermensajeejemplar#resultados";
	}

}
