package com.geraldSara.tarea7dwesGeraldSara.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;
import com.geraldSara.tarea7dwesGeraldSara.servicios.Sesion.Perfil;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorLogin {
	
	@Autowired
	ServiciosFactory factory;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/iniciarSesion")
	public String login(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
		boolean isAuthenticated = factory.getServiciosCredenciales().login(usuario, contrasena);

		if (isAuthenticated) {
			
			session.setAttribute("usuario", usuario);
			
			if (usuario.equals("admin")) {
				session.setAttribute("perfil", Perfil.ADMIN);				
				return "redirect:/menu";
			} else {
				if (factory.getServiciosCredenciales().obtenerCreden(usuario, contrasena).getCliente()!=null) {
					session.setAttribute("perfil", Perfil.CLIENTE);
					return "redirect:/cliente/menu";
				}else {
					session.setAttribute("perfil", Perfil.REGISTRADO);
					return "redirect:/menu";
				}
			}
				
		} else {
			model.addAttribute("error", "Usuario o contraseña incorrectos");
			return "login"; // Vuelve a mostrar la página de login con el error
		}
	}

}
