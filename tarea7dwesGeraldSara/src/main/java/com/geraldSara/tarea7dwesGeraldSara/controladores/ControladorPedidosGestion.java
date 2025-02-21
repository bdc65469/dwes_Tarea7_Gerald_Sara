package com.geraldSara.tarea7dwesGeraldSara.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestion")
public class ControladorPedidosGestion {
	
	@GetMapping("/menuPedidos")
	public String menugestionPedidos() {
		return "menuPedidosAdm";
	}
	
	@GetMapping("/detallesPedido")
	public String detallesPedido() {
		return "detallesPedido";
	}

}
