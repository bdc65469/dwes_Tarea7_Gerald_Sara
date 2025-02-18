package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Credenciales;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Ejemplar;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Mensaje;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Persona;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mensajes")
public class ControladorMensajes {
	
	@Autowired
	ServiciosFactory factory;

	//Llama al menu mensajes
	@GetMapping("/menu")
	public String menumensajes(HttpSession session) {
		String usuario = (String) session.getAttribute("usuario");
		if (usuario == null) {

			return "redirect:/login";
		}
		return "menumensajes";
	}
	
	//Carga los usuarios para obtener sus mensajes
	@GetMapping("/usuarios")
	public String listadoUsuarios(Model model) {

		List<Credenciales> credenciales = factory.getServiciosCredenciales().listaUsuario();

		model.addAttribute("credenciales", credenciales);

		return "vermensajesusuario";
	}

	//Muestra los mensajes por usuarios
	@GetMapping("/mensajesUsuario")
	public String verMensajes(@RequestParam("usuario") String usuario, Model model, RedirectAttributes redirectAttributes) {

	    // Obtener la persona por su usuario
	    Persona p = factory.getServiciosPersona().obtenerPersonaPorUsuario(usuario);

	    // Verificar si la persona existe
	    if (p == null) {
	        model.addAttribute("mensajeError", "El usuario no existe o no tiene mensajes.");
	        return listadoUsuarios(model); // Cargar nuevamente la lista de usuarios
	    }

	    // Obtener los mensajes de la persona
	    List<Mensaje> mensajes = factory.getServiciosMensaje().obtenerMensajesPorPersona(p.getId());

	    // Agregar datos al modelo
	    redirectAttributes.addFlashAttribute("usuarioSeleccionado", usuario);
	    redirectAttributes.addFlashAttribute("mensajes", mensajes);
	    
	    if (mensajes.isEmpty()) {
	        model.addAttribute("mensajeVacio", "El usuario aún no tiene mensajes.");
	    }

	    listadoUsuarios(model);
	    return "redirect:/mensajes/usuarios#mensajes"; 
	}

	
	//Pantalla para crear mensajes
	@GetMapping("/formularioMensaje")
	public String listadoEjemplares(Model model, HttpSession session) {

		String usuario = (String) session.getAttribute("usuario");
		if (usuario == null) {

			return "redirect:/login";
		}

		List<Ejemplar> ejemplares = factory.getServiciosEjemplar().listadoEjemplares();
		model.addAttribute("ejemplares", ejemplares);
		return "registrarmensajes";
	}

	//Registra los mensajes
	@PostMapping("/crearMensaje")
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
	
	//Menu para obtener mensajes por fecha
	@GetMapping("/fecha")
	public String registrarusuario(HttpSession session) {
		String usuario = (String) session.getAttribute("usuario");
		if (usuario == null) {

			return "redirect:/login";
		}
		return "vermensajesfecha";
	}

	//Obtiene los mensajes por fecha
	@GetMapping("/mensajesFecha")
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
