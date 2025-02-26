package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Ejemplar;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Estado;
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
	public String detallesPedido(@RequestParam("id") Long id, Model m) {
		
		Pedido p = factory.getServiciosPedidos().pedidoPorId(id);
		Map<Planta, Integer> resumenPedido = new HashMap<>();
		
		if (p!=null) {
			for (Ejemplar e: p.getEjemplares()) {
				if (resumenPedido.containsKey(e.getPlanta())) {
					resumenPedido.put(e.getPlanta(), resumenPedido.get(e.getPlanta())+1);
				}else {
					resumenPedido.put(e.getPlanta(), 1);
				}
			}
			m.addAttribute("pedido", p);
			m.addAttribute("plantas", resumenPedido);
		}
		
		
		return "detallespedido";
	}
	
	 @PostMapping("/actualizarEstado")
	    public String actualizarEstado(@RequestParam("pedidoId") Long pedidoId,
	                                   @RequestParam("estado") Estado estado, Model model) {

	        // Obtener el pedido con el ID
	        Pedido pedido = factory.getServiciosPedidos().pedidoPorId(pedidoId);

	        if (pedido != null) {
	            // Actualizamos el estado del pedido
	            pedido.setEstado(estado);
	            
	            if (estado == Estado.Cancelado) {
	            	for (Ejemplar e: pedido.getEjemplares()) {
	            		e.setDisponible(true);
	            	}
	            }

	            // Aquí podrías guardar los cambios si es necesario
	            if(factory.getServiciosPedidos().guardarPedido(pedido)!=null) {
	            	 return "redirect:/gestion/menuPedidos";  
	            }else {
	            	model.addAttribute("mensaje", "No se pudo modificar el estado del pedido");
	            }
	        }

	        return "detallespedido";
	       
	    }
	 
	 @GetMapping("/controlStock")
	 public String controlStock(Model m) {
	     List<Planta> plantas = factory.getServiciosPlanta().listaPlantas();
	     
	     // Creamos un mapa para almacenar el total y disponibles por cada planta
	     Map<Planta, List<Integer>> plantasDatos = new HashMap<>();
	     
	     for (Planta p : plantas) {
	    	 
	         // Obtenemos el total de ejemplares y los disponibles para la planta
	         int total = factory.getServiciosEjemplar().listaEjemplaresPorPlanta(p.getId()).size();
	         int disponibles = factory.getServiciosEjemplar().listaEjemplaresDisponiblesPorPlanta(p).size();
	         
	         // Guardamos los valores en una lista
	         List<Integer> datos = new ArrayList<>();
	         datos.add(total);
	         datos.add(disponibles);
	         
	         plantasDatos.put(p, datos);
	     }
	     
	     // Pasamos las plantas y los datos al modelo
	    
	     m.addAttribute("plantasDatos", plantasDatos);
	     
	     return "controlStock";
	 }



}
