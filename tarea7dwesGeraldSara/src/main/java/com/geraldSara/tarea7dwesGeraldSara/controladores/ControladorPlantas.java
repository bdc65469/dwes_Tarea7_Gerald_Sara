package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Planta;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/plantas")
public class ControladorPlantas {

	@Autowired
	ServiciosFactory factory;

	//Menu de plantas
	@GetMapping("/menu")
	public String menuplantas(HttpSession session) {
		return "menuplantas";
	}
	
	//Formulario para registrar plantas
	@GetMapping("/formularioplanta")
	public String registrarplanta() {
		
		return "registrarplanta";
	}

	//Registrar planta
	@PostMapping("/registrarPlanta")
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
	
	//Formulario modificar planta
	@GetMapping("/formulariomodificarPlanta")
	public String modificarplanta(Model model) {

		List<Planta> plantas = factory.getServiciosPlanta().listaPlantas();
		model.addAttribute("plantas", plantas);
		return "modificarplanta";
	}

	//Modificar planta
	@PostMapping("/modificarPlanta")
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
	
	//Listado de plantas
	@GetMapping("/listadoPlantas")
	public String listadoPlantas(Model model) {
		// Llama al servicio para obtener la lista de plantas ordenada por nombre común
		List<Planta> plantas = factory.getServiciosPlanta().listaPlantas();

		// Añade la lista de plantas al modelo
		model.addAttribute("plantas", plantas);

		// Retorna la vista 'listadoPlantas' (listadoPlantas.html)
		return "listadoPlantas";
	}

}
