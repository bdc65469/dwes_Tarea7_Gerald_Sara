package com.geraldSara.tarea7dwesGeraldSara.util;



import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Planta;

@Component
@SessionScope
public class CarritoSesion {
    private Map<Planta, Integer> plantas = new TreeMap<>();

    public Map<Planta, Integer> getPlantas() {
        return plantas;
    }

    public void agregarPlanta(Planta planta, int cantidad) {
        plantas.put(planta, plantas.getOrDefault(planta, 0) + cantidad);
    }

    public void vaciarCarrito() {
        plantas.clear();
    }
    
    public void eliminarPlanta(Planta planta) {
	    plantas.remove(planta);
}
}
