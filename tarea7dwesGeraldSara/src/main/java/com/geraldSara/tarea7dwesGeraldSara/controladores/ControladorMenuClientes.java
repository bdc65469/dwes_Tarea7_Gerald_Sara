package com.geraldSara.tarea7dwesGeraldSara.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorMenuClientes {
	
	@GetMapping("/menuclientes")
	public String mostrarMenuClientes() {
		return "menuCliente";
	}

}
