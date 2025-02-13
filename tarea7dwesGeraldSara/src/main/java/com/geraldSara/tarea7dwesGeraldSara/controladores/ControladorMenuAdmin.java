package com.geraldSara.tarea7dwesGeraldSara.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorMenuAdmin {

	@GetMapping("/menuadmin")
	public String menuadmin(HttpSession session) {
		String usuario = (String) session.getAttribute("usuario");
		if (usuario == null) {

			return "redirect:/login";
		}

		return "menuadmin";
	}

}
