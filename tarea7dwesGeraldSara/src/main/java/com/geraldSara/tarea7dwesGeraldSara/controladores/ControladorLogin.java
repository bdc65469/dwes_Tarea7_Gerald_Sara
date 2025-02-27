package com.geraldSara.tarea7dwesGeraldSara.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;




@Controller
public class ControladorLogin {
	
	@Autowired
	ServiciosFactory factory;
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/sinpermisos")
	public String sinpermisos() {
		return "sinpermisos";
	}
	
	@GetMapping("/cerrarSesion")
	public String cerrarSesion() {
		return "cierreSesion";
	}

}
