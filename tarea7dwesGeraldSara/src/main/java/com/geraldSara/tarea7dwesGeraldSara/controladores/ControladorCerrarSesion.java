package com.geraldSara.tarea7dwesGeraldSara.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class ControladorCerrarSesion {

	@GetMapping
	public String logout(HttpSession session) {
		session.invalidate(); // Cierra la sesión
		return "redirect:/"; // Redirige a la página principal
	}

}
