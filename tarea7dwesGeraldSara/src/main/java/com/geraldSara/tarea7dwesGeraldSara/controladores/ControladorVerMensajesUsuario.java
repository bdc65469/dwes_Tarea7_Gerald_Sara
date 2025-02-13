package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Credenciales;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Mensaje;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Persona;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;

@Controller
public class ControladorVerMensajesUsuario {

	@Autowired
	ServiciosFactory factory;

	@GetMapping("/vermensajesusuario")
	public String listadoUsuarios(Model model) {

		List<Credenciales> credenciales = factory.getServiciosCredenciales().listaUsuario();

		model.addAttribute("credenciales", credenciales);

		return "vermensajesusuario";
	}

	@PostMapping("/vermensajes")
	public String verMensajes(@RequestParam String usuario, Model model, RedirectAttributes redirectAttributes) {

		Persona p = factory.getServiciosPersona().obtenerPersonaPorUsuario(usuario);
		List<Mensaje> mensajes = factory.getServiciosMensaje().obtenerMensajesPorPersona(p.getId());

		redirectAttributes.addFlashAttribute("usuarioSeleccionado", usuario);
		redirectAttributes.addFlashAttribute("mensajes", mensajes);

		if (mensajes.isEmpty()) {
			redirectAttributes.addFlashAttribute("mensajeVacio", "El usuario aún no tiene mensajes.");
		}

		listadoUsuarios(model);
		return "redirect:/vermensajesusuario#mensajes";
	}

}
