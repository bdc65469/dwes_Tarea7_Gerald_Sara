package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Planta;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;

@Controller
public class ControladorListadoPlantas {

	@Autowired
	ServiciosFactory factory;

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
