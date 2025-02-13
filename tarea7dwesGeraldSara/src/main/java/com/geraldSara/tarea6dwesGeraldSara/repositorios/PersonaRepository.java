package com.geraldSara.tarea6dwesGeraldSara.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geraldSara.tarea6dwesGeraldSara.modelo.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

	/**
	 * Comprueba si existe un email
	 * 
	 * @param email Email a buscar
	 * @return True si existe el email, false si no existe el email
	 */
	boolean existsByEmail(String email);

	/**
	 * Busca persona por el id
	 * 
	 * @param id Id de la persona a buscar
	 * @return Devuelve la persona
	 */
	Persona findPersonaById(Long id);

}
