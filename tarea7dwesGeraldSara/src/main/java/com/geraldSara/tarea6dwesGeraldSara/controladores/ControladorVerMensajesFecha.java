package com.geraldSara.tarea6dwesGeraldSara.controladores;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geraldSara.tarea6dwesGeraldSara.modelo.Mensaje;
import com.geraldSara.tarea6dwesGeraldSara.servicios.ServiciosFactory;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorVerMensajesFecha {

	@Autowired
	ServiciosFactory factory;

	@GetMapping("/vermensajesfecha")
	public String registrarusuario(HttpSession session) {
		String usuario = (String) session.getAttribute("usuario");
		if (usuario == null) {

			return "redirect:/login";
		}
		return "vermensajesfecha";
	}

	@PostMapping("/buscarmensajesfecha")
	public String crearUsuario(@RequestParam String fechaInicial, @RequestParam String fechaFinal, Model model) {

		if (factory.getComprobaciones().convertirFechaInicio(fechaInicial) != null
				&& factory.getComprobaciones().convertirFechaFin(fechaFinal) != null) {

			LocalDateTime fechaIni = factory.getComprobaciones().convertirFechaInicio(fechaInicial);
			LocalDateTime fechaFin = factory.getComprobaciones().convertirFechaFin(fechaFinal);

			List<Mensaje> mensajesFecha = factory.getServiciosMensaje().obtenerMensajesPorFecha(fechaIni, fechaFin);
			if (mensajesFecha.isEmpty()) {
				model.addAttribute("mensaje", "No hay mensajes registrados en ese rango de fechas");
			} else {
				model.addAttribute("listadoMensajes", mensajesFecha);
			}

		} else {
			model.addAttribute("mensaje", "Error al obtener las fechas");
		}

		return "vermensajesfecha";

	}

}
