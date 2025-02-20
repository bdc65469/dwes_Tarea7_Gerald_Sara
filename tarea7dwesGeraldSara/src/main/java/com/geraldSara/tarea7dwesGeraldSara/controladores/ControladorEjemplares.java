package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Ejemplar;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Mensaje;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Persona;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Planta;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ejemplares")
public class ControladorEjemplares {

	@Autowired
	ServiciosFactory factory;
	
	//Menu ejemplares
	@GetMapping("/menu")
	public String menuejemplares(HttpSession session) {
		
		return "menuejemplares";
	}
	
	//Formulario de crear ejemplares
	@GetMapping("/listaPlantas")
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
	
	//Crear el ejemplar
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
	
	//Listado de plantas para elegir
	@GetMapping("/listaEjemplaresPlanta")
	public String listadoPlantas(Model model) {

		List<Planta> plantas = factory.getServiciosPlanta().listaPlantas();

		model.addAttribute("plantas", plantas);

		return "verejemplaresplanta";
	}
	
	//Ejemplares por planta
	@GetMapping("/ejemplaresPlanta")
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

		return "redirect:/ejemplares/listaEjemplaresPlanta#ejemplares";
	}
	
	//Lista los ejemplares
	@GetMapping("/listaEjemplares")
	public String listadoEjemplares(Model model, HttpSession session) {

		String usuario = (String) session.getAttribute("usuario");
		if (usuario == null) {

			return "redirect:/login";
		}

		List<Ejemplar> ejemplares = factory.getServiciosEjemplar().listadoEjemplares();
		model.addAttribute("ejemplares", ejemplares);
		return "vermensajeejemplar";
	}

	@GetMapping("/mensajesEjemplar")
	public String crearEjemplar(@RequestParam("id") Long id, RedirectAttributes redirectAttributes, HttpSession session,
			Model model) {

		List<Mensaje> listadoMensajes = factory.getServiciosMensaje().obtenerMensajesPorIdEjemplar(id);

		// Uso de redirect:, los atributos agregados al model no se mantienen porque la
		// redirección hace una nueva solicitud HTTP y los datos originales no se
		// transfieren automáticamente
		redirectAttributes.addFlashAttribute("listadoMensajes", listadoMensajes);

		listadoEjemplares(model, session);
		return "redirect:/ejemplares/listaEjemplares#resultados";
	}

}
