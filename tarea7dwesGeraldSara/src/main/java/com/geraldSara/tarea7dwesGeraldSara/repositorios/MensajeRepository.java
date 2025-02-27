package com.geraldSara.tarea7dwesGeraldSara.repositorios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Cliente;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Ejemplar;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

	/**
	 * Filtra mensajes por ejemplar
	 * 
	 * @param idEjemplar id del ejemplar a buscar
	 * @return Lista de mensajes
	 */
	@Query("SELECT m FROM Mensaje m JOIN m.ejemplar e WHERE e.id = :idEjemplar ORDER BY m.fechahora DESC")
	List<Mensaje> findByEjemplarIdOrderByFechaHora(@Param("idEjemplar") Long idEjemplar);

	/**
	 * Filtra los mensajes realizados por una persona
	 * 
	 * @param idPersona id de la persona a buscar
	 * @return Lista de mensajes
	 */
	@Query("SELECT m FROM Mensaje m JOIN m.persona p WHERE p.id = :idPersona")
	List<Mensaje> obtenerMensajesPorPersona(@Param("idPersona") Long idPersona);

	/**
	 * Filtra mensajes por fecha
	 * 
	 * @param fechaInicio Fecha inicial
	 * @param fechaFin    Fecha final
	 * @return Una lista de mensajes
	 */
	@Query("SELECT m FROM Mensaje m WHERE m.fechahora BETWEEN :fechaInicio AND :fechaFin ORDER BY m.fechahora DESC")
	List<Mensaje> obtenerMensajesPorRangoDeFecha(@Param("fechaInicio") LocalDateTime fechaInicio,
			@Param("fechaFin") LocalDateTime fechaFin);

	/**
	 * Filtra mensajes por planta
	 * 
	 * @param codigo Codigo de la planta a buscar
	 * @return Lista de mensajes
	 */
	@Query("SELECT m FROM Mensaje m " + "JOIN m.ejemplar e " + "JOIN e.planta p " + "WHERE p.codigo = :codigo")
	List<Mensaje> obtenerMensajesPorPlanta(@Param("codigo") String codigo);
	
	List<Mensaje> findByCliente (Cliente c);

	List<Mensaje> findByEjemplar(Ejemplar e);

}
