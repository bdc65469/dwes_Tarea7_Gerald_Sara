package com.geraldSara.tarea7dwesGeraldSara.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	boolean existsByNif(String nif);
	
	Cliente findClienteById(Long id);

	boolean existsByEmail(String email);

}
