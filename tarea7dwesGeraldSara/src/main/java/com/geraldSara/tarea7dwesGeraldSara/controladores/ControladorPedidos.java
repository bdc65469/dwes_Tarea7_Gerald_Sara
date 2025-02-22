package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Planta;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;

@Controller
@RequestMapping("/pedidos")
public class ControladorPedidos {
	
	@Autowired
	ServiciosFactory factory;
	
	@GetMapping("/mispedidos")
	public String mostrarmisPedidos() {
		return "misPedidos";
	}
	
	@GetMapping("/realizarpedido")
	public String realizarPedido() {
		return "carrito";
	}
	
	//Muestra la lista de los ejemplares disponibles
	@GetMapping("/stock")
	public String stock(Model model) {
		
		List<Planta> plantas = factory.getServiciosPlanta().listaPlantas();
		Map<Planta, Long> ejemplaresPlanta = new HashMap<Planta, Long>();
		for (int i=0; i<plantas.size(); i++) {
			ejemplaresPlanta.put(plantas.get(i), factory.getServiciosEjemplar().numEjemplaresPorPlanta(plantas.get(i)));
		}
		
	    model.addAttribute("ejemplares", ejemplaresPlanta);
		return "listaParaComprar";
	}
	

}
