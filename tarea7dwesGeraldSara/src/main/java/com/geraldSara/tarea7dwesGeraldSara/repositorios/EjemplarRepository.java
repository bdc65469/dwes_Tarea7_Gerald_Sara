package com.geraldSara.tarea7dwesGeraldSara.repositorios;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Ejemplar;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Planta;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {

	/**
	 * Obtiene el id del último ejemplar de esa planta y le suma uno
	 * 
	 * @param p Planta a buscar
	 * @return Un long que es el id que llevará el siguiente ejemplar creado
	 */
	default Long ultimoIdEjemplarByPlanta(Planta p) {
		List<Ejemplar> lista = findAll();
		if (!lista.isEmpty()) {
			long ret = 0;
			for (Ejemplar e : lista) {
				if (e.getPlanta().getId().equals(p.getId())) {
					ret++;
				}
			}
			return ret + 1;
		}

		return 1L;

	}

	/**
	 * Devuelve la lista de ejemplares de una planta
	 * 
	 * @param codigo Codigo de la planta
	 * @return Un conjunto de ejemplares de una planta
	 */
	Set<Ejemplar> findByPlantaCodigo(String codigo);

}
