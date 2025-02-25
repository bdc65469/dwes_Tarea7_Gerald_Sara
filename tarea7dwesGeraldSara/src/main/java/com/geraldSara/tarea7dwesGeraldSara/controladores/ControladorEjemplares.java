package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

	// Menu ejemplares
	@GetMapping("/menu")
	public String menuejemplares(HttpSession session) {

		return "menuejemplares";
	}

	// Formulario de crear ejemplares
	@GetMapping("/listaPlantas")
	public String listadoPlantas(Model model) {

		// Llama al servicio para obtener la lista de plantas ordenada por nombre común
		List<Planta> plantas = factory.getServiciosPlanta().listaPlantas();

		// Añade la lista de plantas al modelo
		model.addAttribute("plantas", plantas);

		// Retorna la vista 'crearejemplar' (crearejemplar.html)
		return "crearejemplar";
	}

	// Crear el ejemplar
	@PostMapping("/crearejemplar")
	public String crearEjemplar(@RequestParam("id") Long id, Model model,
			@AuthenticationPrincipal UserDetails userDetails) {

		String usuario = userDetails.getUsername();
		// El usuario está presente en la sesión
		Persona p = factory.getServiciosPersona().obtenerPersonaPorUsuario(usuario);
		if (factory.getServiciosEjemplar().crearEjemplarYMensaje(id, p) != null) {
			model.addAttribute("mensajeC", "Nuevo ejemplar registrado con éxito");
		} else {
			model.addAttribute("mensaje", "Error al actualizar los datos de la planta");
		}

		listadoPlantas(model);
		return "crearejemplar";
	}

	// Listado de plantas para elegir
	@GetMapping("/listaEjemplaresPlanta")
	public String listadoPlantas1(Model model) {

		List<Planta> plantas = factory.getServiciosPlanta().listaPlantas();

		model.addAttribute("plantas", plantas);

		return "verejemplaresplanta";
	}

	// Ejemplares por planta
	@GetMapping("/ejemplaresPlanta")
	public String listarEjemplaresPlanta(
	        @RequestParam(name = "plantasSeleccionadas", required = false) List<Long> plantasSeleccionadas,
	        Model model, RedirectAttributes redirectAttributes) {

	    Map<Planta, Set<Ejemplar>> ejemplaresPorPlanta = new HashMap<>();
	    Map<Long, Integer> mensajesPorEjemplar = new HashMap<>();
	    Map<Long, LocalDateTime> ultimoMensajePorEjemplar = new HashMap<>();

	    if (plantasSeleccionadas != null) {
	        for (Long id : plantasSeleccionadas) {
	            Planta planta = factory.getServiciosPlanta().obtenerPlantaporId(id);
	            if (planta != null) {
	                Set<Ejemplar> ejemplares = factory.getServiciosEjemplar()
	                        .filtarEjemplaresPlanta(planta.getCodigo());
	                
	                // Guardar los ejemplares en el mapa principal
	                ejemplaresPorPlanta.put(planta, ejemplares);

	                // Para cada ejemplar, obtener sus mensajes
	                for (Ejemplar ejemplar : ejemplares) {
	                    List<Mensaje> mensajes = factory.getServiciosMensaje().obtenerMensajesPorEjemplar(ejemplar);
	                    mensajesPorEjemplar.put(ejemplar.getId(), mensajes.size());

	                    // Obtener la fecha del último mensaje si existen mensajes
	                    if (!mensajes.isEmpty()) {
	                    	mensajes.sort(Comparator.comparing(Mensaje::getFechahora).reversed());
	                        ultimoMensajePorEjemplar.put(ejemplar.getId(), mensajes.get(0).getFechahora());
	                    } else {
	                        ultimoMensajePorEjemplar.put(ejemplar.getId(), null);
	                    }
	                }
	            }
	        }
	    }

	    listadoPlantas(model);
	    redirectAttributes.addFlashAttribute("ejemplaresPorPlanta", ejemplaresPorPlanta);
	    redirectAttributes.addFlashAttribute("mensajesPorEjemplar", mensajesPorEjemplar);
	    redirectAttributes.addFlashAttribute("ultimoMensajePorEjemplar", ultimoMensajePorEjemplar);

	    return "redirect:/ejemplares/listaEjemplaresPlanta#ejemplares";
	}


	// Lista los ejemplares
	@GetMapping("/listaEjemplares")
	public String listadoEjemplares(Model model, HttpSession session) {

		List<Ejemplar> ejemplares = factory.getServiciosEjemplar().listadoEjemplares();
		model.addAttribute("ejemplares", ejemplares);
		return "vermensajeejemplar";
	}

	@GetMapping("/mensajesEjemplar")
	public String mensajesEjemplar(@RequestParam("id") Long id, RedirectAttributes redirectAttributes, HttpSession session,
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
