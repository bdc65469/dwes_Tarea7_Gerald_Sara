package com.geraldSara.tarea7dwesGeraldSara.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Credenciales;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Persona;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorRegistrarUsuario {

	@Autowired
	ServiciosFactory factory;

	@GetMapping("/registrarusuario")
	public String registrarusuario(HttpSession session) {
		String usuario = (String) session.getAttribute("usuario");
		if (usuario == null) {

			return "redirect:/login";
		}
		return "registrarusuario";
	}

	@PostMapping("/registrarusuario")
	public String crearUsuario(@RequestParam String nombre, @RequestParam String email, @RequestParam String usuario,
			@RequestParam String contrasena, Model model) {

		boolean valido = true;
		if (!factory.getComprobaciones().verificarNombrePersona(nombre)) {
			model.addAttribute("errorNombre", "Nombre no válido");
			valido = false;
		}

		if (!factory.getComprobaciones().esEmailValido(email)) {
			model.addAttribute("errorEmail", "Email no válido");
			valido = false;
		}

		if (factory.getServiciosPersona().existeEmail(email)) {
			model.addAttribute("errorEmail", "Email ya registrado");
			valido = false;
		}

		if (factory.getComprobaciones().comprobarUsuario(usuario)) {
			model.addAttribute("errorUsuario", "El nombre de usuario no puede contener espacios en blanco");
			valido = false;
		}

		if (usuario.equalsIgnoreCase("admin")) {
			model.addAttribute("errorUsuario", "Nombre de usuario reservado");
			valido = false;
		}

		if (factory.getServiciosCredenciales().existeUsuario(usuario)) {
			model.addAttribute("errorUsuario", "El usuario ya existe");
			valido = false;
		}

		if (factory.getComprobaciones().comprobarEspaciosBlanco(contrasena)) {
			model.addAttribute("errorContrasena", "La contraseña no puede contener espacios en blanco");
			valido = false;
		}

		if (!factory.getComprobaciones().esContrasenaValida(contrasena)) {
			model.addAttribute("errorContrasena", "Contraseña no válida. Debe tener, mínimo, 6 carácteres");
			valido = false;
		}

		if (valido) {
			Persona p1 = new Persona(nombre, email);
			Credenciales c1 = new Credenciales(usuario, contrasena);

			if (factory.getServiciosPersona().crearUsuario(p1, c1) != null) {
				model.addAttribute("mensajeC", "Usuario creado correctamente");
			} else {
				model.addAttribute("mensaje", "Error al registrar el nuevo usuario");
			}
		}

		return "registrarusuario";

	}

}
