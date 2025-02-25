package com.geraldSara.tarea7dwesGeraldSara.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Cliente;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	Pedido findPedidoById (Long id);
	
	List<Pedido> findByCliente (Cliente c);

	List<Pedido> findByOrderByFechaDesc();

}
