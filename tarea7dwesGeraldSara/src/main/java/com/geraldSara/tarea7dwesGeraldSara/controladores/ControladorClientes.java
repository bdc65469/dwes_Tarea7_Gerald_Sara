package com.geraldSara.tarea7dwesGeraldSara.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Cliente;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Credenciales;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Pedido;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Planta;
import com.geraldSara.tarea7dwesGeraldSara.modelo.PlantaDTO;
import com.geraldSara.tarea7dwesGeraldSara.modelo.Rol;
import com.geraldSara.tarea7dwesGeraldSara.servicios.ServiciosFactory;
import com.geraldSara.tarea7dwesGeraldSara.util.CarritoSesion;

@Controller
@RequestMapping("/cliente")
public class ControladorClientes {

	@Autowired
	ServiciosFactory factory;

	@Autowired
	private CarritoSesion carritoSesion;

	@ModelAttribute("carrito")
	public List<PlantaDTO> crearCarrito() {
		return new ArrayList<>(); // Se crea un carrito vacío al iniciar sesión
	}


	// PlantaDTO tiene id de planta y una cantidad
	@GetMapping("/carrito")
	public String agreagarCarrito(@ModelAttribute("carrito") List<PlantaDTO> carrito, Model model) {
		Map<Planta, Integer> plantas = carritoSesion.getPlantas();
		for (PlantaDTO p : carrito) {
			Planta plan = factory.getServiciosPlanta().obtenerPlantaporId(p.getId());
			plantas.put(plan, p.getCantidad());
		}
		model.addAttribute("carrito", plantas);
		return "carrito";
	}

	@PostMapping("/agregarCarrito")
	public String agregarAlCarrito(@RequestParam Map<String, String> cantidades) {
		for (Map.Entry<String, String> entry : cantidades.entrySet()) {
			if (entry.getKey().equals("_csrf")) {
				continue; // Ignorar token CSRF
			}

			try {
				Long plantaId = Long.parseLong(entry.getKey().replace("cantidades_", ""));
				int cantidad = Integer.parseInt(entry.getValue());

				if (cantidad > 0) {
					Planta planta = factory.getServiciosPlanta().obtenerPlantaporId(plantaId);
					carritoSesion.agregarPlanta(planta, cantidad);
				}
			} catch (NumberFormatException e) {
				System.out.println("Error procesando la cantidad o el ID: " + entry.getKey());
			}
		}
		return "redirect:/cliente/carrito";
	}

	@GetMapping("/vercarrito")
	public String verCarrito(Model model) {
		model.addAttribute("carrito", carritoSesion.getPlantas());
		return "carrito";
	}

	@GetMapping("/eliminarCarrito")
	public String eliminarDelCarrito(@RequestParam("plantaId") Long plantaId) {
	    Planta planta = factory.getServiciosPlanta().obtenerPlantaporId(plantaId);
	    carritoSesion.eliminarPlanta(planta);
	    return "redirect:/cliente/carrito"; // Redirige de nuevo al carrito
	}

	@GetMapping ("/detallesPedidoRealizado")
	public String detalles () {
		return "pedidoRealizado";
	}

	@PostMapping("/hacerPedido")
	public String hacerPedido(@RequestParam Map<String, String> params, 
			@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirect) {
		
		if (!params.isEmpty()) {
			Cliente c = factory.getServiciosClientes().obtenerClientePorUsuario(userDetails.getUsername());
			Pedido nuevo = new Pedido (c);
			Map <Planta, Integer> lista = new HashMap <Planta, Integer>();
			if(factory.getServiciosPedidos().crearPedido(nuevo)!=null) {
				for (String key : params.keySet()) {

					if (key.contains("plantaId")) {

						Planta p = factory.getServiciosPlanta().obtenerPlantaporId(Long.valueOf(params.get(key)));
						String cantidadKey = key.replace("plantaId", "cantidad"); // Obtener la cantidad correspondiente
						Integer cantidad = Integer.valueOf(params.get(cantidadKey));
						lista.put(p, cantidad);
						
						System.out.println(cantidad);

						factory.getServiciosPedidos().asignarEjemplares(p, cantidad, c, nuevo);											
						
					}
				}
			}
			carritoSesion.getPlantas().clear();
			redirect.addFlashAttribute("cliente", c);
			redirect.addFlashAttribute("pedido", nuevo);
			redirect.addFlashAttribute("detallesPedido", lista);
			return "redirect:/cliente/detallesPedidoRealizado";
		}

		return "menuCliente";

	}

	@GetMapping("/formularioRegistro")
	public String registrarusuario() {

		return "registrarcliente";
	}

	@PostMapping("/registrarCliente")
	public String crearCliente(@RequestParam String nombre, @RequestParam String fechaNac, @RequestParam String email,
			@RequestParam String nif, @RequestParam String direccion, @RequestParam String telefono,
			@RequestParam String usuario, @RequestParam String contrasena, Model model) {

		boolean valido = true;
		LocalDate fechaNaci = null;
		if (!factory.getComprobaciones().verificarNombrePersona(nombre)) {
			model.addAttribute("errorNombre", "Nombre no válido");
			valido = false;
		}

		if (fechaNac.equals("")) {
			model.addAttribute("errorFecha", "Debes introducir una fecha");
			valido = false;
		} else {
			fechaNaci = factory.getComprobaciones().convertirFechaNac(fechaNac);
		}

		if (!factory.getComprobaciones().esEmailValido(email)) {
			model.addAttribute("errorEmail", "Email no válido");
			valido = false;
		}

		if (factory.getServiciosClientes().existeEmail(email)) {
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
			Cliente cli1 = new Cliente(nombre, fechaNaci, nif, direccion, email, telefono);
			Credenciales c1 = new Credenciales(usuario, contrasena, Rol.ROLE_CLIENTE, cli1);

			if (factory.getServiciosCredenciales().registrarCliente(c1.getUsuario(), c1.getPassword(), c1.getRol(),
					cli1) != null) {
				return "redirect:/cliente/registrado";
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

}
