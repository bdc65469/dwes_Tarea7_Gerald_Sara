package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Cliente;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Credenciales;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;


@Controller
@RequestMapping("/cliente")
public class ControladorClientes {
	
	@Autowired
	ServiciosFactory factory;
	
	@GetMapping("/formularioRegistro")
	public String registrarusuario() {
		
		return "registrarcliente";
	}
	
	@PostMapping("/registrarCliente")
	public String crearCliente(@RequestParam String nombre,@RequestParam String fechaNac, @RequestParam String email, @RequestParam String nif, @RequestParam String direccion, @RequestParam String telefono, @RequestParam String usuario,
			@RequestParam String contrasena, Model model) {

		boolean valido = true;
		LocalDate fechaNaci = null;
		if (!factory.getComprobaciones().verificarNombrePersona(nombre)) {
			model.addAttribute("errorNombre", "Nombre no válido");
			valido = false;
		}
		
		if (fechaNac.equals("")) {
			model.addAttribute("errorFecha", "Debes introducir una fecha");
			valido = false;
		}else {
			fechaNaci = factory.getComprobaciones().convertirFechaNac(fechaNac);
		}

		if (!factory.getComprobaciones().esEmailValido(email)) {
			model.addAttribute("errorEmail", "Email no válido");
			valido = false;
		}

		if (factory.getServiciosPersona().existeEmail(email)) {
			model.addAttribute("errorEmail", "Email ya registrado");
			valido = false;
		}
		
		if (!factory.getComprobaciones().comprobarDniNie(nif)) {
			model.addAttribute("errorNif", "Nif o nie no válido");
			valido = false;
		}
		
		if (factory.getServiciosClientes().existeNif(nif)) {
			model.addAttribute("errorNif", "Nif o nie registrado");
			valido = false;
		}
		
		if (!factory.getComprobaciones().verificarTelefono(telefono)) {
			model.addAttribute("errorTelefono", "Solo puede contener números");
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
			Cliente cli1 = new Cliente (nombre, fechaNaci, nif, direccion, email, telefono);
			Credenciales c1 = new Credenciales(usuario, contrasena);

			if (factory.getServiciosClientes().crearCliente(cli1, c1)!=null) {
				return "redirect:/clientes/registrado";
			} else {
				model.addAttribute("mensaje", "Error al registrar el nuevo usuario");
			}
		}

		return "registrarcliente";

	}
	
	@GetMapping("/registrado")
	public String loginCorrecto() {
		
		return "loginCorrecto";
	}
	
	@GetMapping("/menu")
	public String mostrarMenuClientes() {
		return "menuCliente";
	}
	
	@GetMapping("/carrito")
	public String mostrarCarrito() {
		return "carrito";
	}

}
