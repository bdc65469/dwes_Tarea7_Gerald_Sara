package com.geraldSara.tarea7dwesGeraldSara.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedidos")
public class ControladorPedidos {
	
	@GetMapping("/mispedidos")
	public String mostrarmisPedidos() {
		return "misPedidos";
	}

}
