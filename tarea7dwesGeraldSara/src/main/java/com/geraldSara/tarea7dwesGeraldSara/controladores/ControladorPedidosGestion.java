package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Ejemplar;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Pedido;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Planta;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;

@Controller
@RequestMapping("/gestion")
public class ControladorPedidosGestion {
	
	@Autowired
	ServiciosFactory factory;
	
	@GetMapping("/menuPedidos")
	public String menugestionPedidos(Model model) {
		
	        List<Pedido> listaPedidos = factory.getServiciosPedidos().pedidosFecha();

	        // Mapa para cada pedido con la cantidad de ejemplares por planta
	        Map<Long, Map<Planta, Integer>> pedidosResumen = new HashMap<>();

	        for (Pedido pedido : listaPedidos) {
	            Map<Planta, Integer> resumenPedido = new HashMap<>();

	            for (Ejemplar ejemplar : pedido.getEjemplares()) {
	                Planta planta = ejemplar.getPlanta();
	                resumenPedido.put(planta, resumenPedido.getOrDefault(planta, 0) + 1);
	            }

	            pedidosResumen.put(pedido.getId(), resumenPedido);
	        }

	        model.addAttribute("pedidos", listaPedidos);
	        model.addAttribute("pedidosResumen", pedidosResumen);
	    
		return "menuPedidosAdm";
	}
	
	
	@GetMapping("/detallesPedido")
	public String detallesPedido() {
		return "detallesPedido";
	}

}
