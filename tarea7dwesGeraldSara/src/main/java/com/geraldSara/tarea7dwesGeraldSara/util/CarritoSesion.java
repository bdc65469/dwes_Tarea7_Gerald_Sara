package com.geraldSara.tarea7dwesGeraldSara.util;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Planta;

@Component
@SessionScope
public class CarritoSesion {
    private Map<Planta, Integer> plantas = new HashMap<>();

    public Map<Planta, Integer> getPlantas() {
        return plantas;
    }

    public void agregarPlanta(Planta planta, int cantidad) {
        plantas.put(planta, plantas.getOrDefault(planta, 0) + cantidad);
    }

    public void vaciarCarrito() {
        plantas.clear();
    }
}
