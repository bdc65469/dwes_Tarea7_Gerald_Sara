package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Credenciales;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Mensaje;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Persona;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mensajes")
public class ControladorMensajes {
	
	@Autowired
	ServiciosFactory factory;

	@GetMapping("/menu")
	public String menumensajes(HttpSession session) {
		String usuario = (String) session.getAttribute("usuario");
		if (usuario == null) {

			return "redirect:/login";
		}
		return "menumensajes";
	}
	
	@GetMapping("/usuarios")
	public String listadoUsuarios(Model model) {

		List<Credenciales> credenciales = factory.getServiciosCredenciales().listaUsuario();

		model.addAttribute("credenciales", credenciales);

		return "vermensajesusuario";
	}

	@GetMapping("/mensajesUsuario")
	public String verMensajes(@RequestParam("usuario") String usuario, Model model, RedirectAttributes redirectAttributes) {

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
