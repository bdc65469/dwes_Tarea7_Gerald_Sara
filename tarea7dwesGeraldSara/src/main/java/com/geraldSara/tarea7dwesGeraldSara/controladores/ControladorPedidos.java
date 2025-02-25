package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Cliente;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Ejemplar;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Pedido;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Planta;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;
import com.geraldSara.tarea7dwesGeraldSara.util.CarritoSesion;

@Controller
@RequestMapping("/pedidos")
public class ControladorPedidos {

	@Autowired
	ServiciosFactory factory;

	@Autowired
	private CarritoSesion carritoSesion;

	@GetMapping("/mispedidos")
	public String mostrarmisPedidos(Model model, @AuthenticationPrincipal UserDetails userDetails) {
	    Cliente c = factory.getServiciosClientes().obtenerClientePorUsuario(userDetails.getUsername());
	    if (c != null) {
	        List<Pedido> listaPedidos = factory.getServiciosPedidos().pedidosCliente(c);

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
	    }
	    return "misPedidos";
	}
	
	@GetMapping("/realizarpedido")
	public String realizarPedido() {
		return "carrito";
	}

	// Muestra la lista de los ejemplares disponibles
	@GetMapping("/stock")
	public String stock(Model model) {

		List<Planta> plantas = factory.getServiciosPlanta().listaPlantas();
		Map<Planta, Integer> ejemplaresPlanta = new TreeMap<Planta, Integer>();
		for (int i = 0; i < plantas.size(); i++) {
			ejemplaresPlanta.put(plantas.get(i), factory.getServiciosEjemplar().numEjemplaresPorPlanta(plantas.get(i)));
		}

		for (Map.Entry<Planta, Integer> entry : carritoSesion.getPlantas().entrySet()) {
			Planta planta = entry.getKey();
			int valorARestar = entry.getValue();

			// Verificar si la planta está en ejemplaresPlanta antes de restar
			if (ejemplaresPlanta.containsKey(planta)) {
				int nuevoValor = ejemplaresPlanta.get(planta) - valorARestar;
				ejemplaresPlanta.put(planta, nuevoValor);
			}
		}

		model.addAttribute("ejemplares", ejemplaresPlanta);
		return "listaParaComprar";
	}

}
