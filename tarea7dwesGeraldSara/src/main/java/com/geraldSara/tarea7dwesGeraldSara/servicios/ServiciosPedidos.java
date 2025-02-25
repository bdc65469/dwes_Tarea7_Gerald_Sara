package com.geraldSara.tarea7dwesGeraldSara.servicios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Cliente;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Ejemplar;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Mensaje;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Pedido;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Planta;
import com.geraldSara.tarea7dwesGeraldSara.repositorios.PedidoRepository;


@Service
public class ServiciosPedidos {
	@Autowired
	Comprobaciones comprobaciones;

	@Autowired
	private PedidoRepository repoPedido;

	@Autowired
	private ServiciosEjemplar servEjemplar;

	@Autowired
	private ServiciosMensaje servMensaje;
	
	
	public Pedido crearPedido (Pedido p) {
		return repoPedido.save(p);
	}

	@Transactional
	public boolean asignarEjemplares(Planta p, int cantidad, Cliente c, Pedido pe) {
		

		List<Ejemplar> ejemplares = servEjemplar.listaEjemplaresDisponiblesPorPlanta(p);

		Pedido pedido = repoPedido.findPedidoById(pe.getId());
		
		
			for (int i = 0; i < cantidad; i++) {
				ejemplares.get(i).setDisponible(false);
				ejemplares.get(i).setPedido(pedido);
				if (servEjemplar.actualizarEjemplar(ejemplares.get(i))!=null) {
					String mensaje = "El cliente " + c.getNombre() + " compró el ejemplar " + ejemplares.get(i).getNombre()
							+ " el día " + comprobaciones.formatoFecha(LocalDateTime.now()) + " en el pedido " + pedido.getId();
					Mensaje m = new Mensaje(LocalDateTime.now(), mensaje, ejemplares.get(i), c);
					if (servMensaje.crearMensaje(m) == null) {
						return false;
					}
				}else {
					return false;
				}
				
			}
		
		
		
		
		

		return true;
	}

}
