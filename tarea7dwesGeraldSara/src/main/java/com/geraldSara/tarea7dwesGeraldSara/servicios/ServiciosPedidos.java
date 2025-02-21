package com.geraldSara.tarea7dwesGeraldSara.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geraldSara.tarea7dwesGeraldSara.repositorios.PedidoRepository;

@Service
public class ServiciosPedidos {
	
	@Autowired
	private PedidoRepository repoPedido;
	
	

}
