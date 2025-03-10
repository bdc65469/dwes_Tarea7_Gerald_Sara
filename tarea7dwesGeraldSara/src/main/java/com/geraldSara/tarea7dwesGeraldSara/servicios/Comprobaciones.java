package com.geraldSara.tarea7dwesGeraldSara.servicios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class Comprobaciones {

	public Comprobaciones() {
	};

	/**
	 * verifica si el nombre de la persona es correcto, no puede contener números,
	 * ni carácteres, ni tabulaciones
	 * 
	 * @param nombre Nombre de la persona
	 * @return true si es correcto, false si es incorrecto
	 */
	public boolean verificarNombrePersona(String nombre) {
		return nombre != null && nombre.matches("^[a-zA-ZÀ-ÿÑñ']+( [a-zA-ZÀ-ÿÑñ']+)*$") && nombre.length() <= 50;
	}

	/**
	 * Comprueba si sigue la estructura del email
	 * 
	 * @param email Email del usuario
	 * @return True si el email es válido, false si no es válido
	 */
	public boolean esEmailValido(String email) {
		return email != null && email.length() <= 50
				&& email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
	}

	/**
	 * Método para comprobar que el codigo no contiene ni espacios, ni caracteres
	 * especiales
	 * 
	 * @param code Código de la planta
	 * @return True si el código es válido, False si el código no es válido
	 */

	public boolean comprobarEspaciosBlanco(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		return s.contains(" ");
	}

	/**
	 * Comprueba si usuario tiene espacios en blanco, tabulaciones, es nulo o más de
	 * 50 carácteres
	 * 
	 * @param s usuario de la persona
	 * @return true si no es válido, false si es valido
	 */
	public boolean comprobarUsuario(String s) {
		return s == null || s.length() == 0 || s.length() > 50 || s.matches(".*\\s.*");
	}

	/**
	 * Metodo que comprueba si el string introducido contiene solo espacios o
	 * numeros y la longitud del string
	 * 
	 * @param nombre String a comprobar
	 * @return True si el formato del string es correcto, false si es incorrecto
	 */
	public boolean nombreValido(String nombre) {

		if (nombre == null || nombre.trim().isEmpty() || nombre.length() > 100) {
			return false;
		}
		if (nombre.matches(".*\\d.*")) {
			return false;
		}
		return true;
	}

	/**
	 * Metodo que comprueba si el string es valido. No puede contener ni espacios,
	 * ni numeros
	 * 
	 * @param codigo Codigo introducido
	 * @return True si el formato del string es correcto, false si es incorrecto
	 */
	public boolean esCodigoValido(String codigo) {

		if (codigo == null || codigo.isEmpty() || codigo.length() > 50) {
			return false;
		}
		return codigo.matches("[a-zA-Z]+");
	}

	/**
	 * Metodo para comprobar que la contraseña sea válida, debe contener mínimo 6
	 * caractes y maximo 50, no contener espacios en blanco, ni tabulaciones y un
	 * número y una letra como mínimo
	 * 
	 * @param contrasena
	 * @return true si la contraseña es válida, false si no es válida
	 */
	public boolean esContrasenaValida(String contrasena) {
		if (contrasena == null || contrasena.length() < 6 || contrasena.length() > 50
				|| contrasena.matches(".*\\s.*")) {
			return false;
		}

		boolean tieneLetra = false;
		boolean tieneNumero = false;

		for (char c : contrasena.toCharArray()) {
			if (Character.isLetter(c)) {
				tieneLetra = true;
			}
			if (Character.isDigit(c)) {
				tieneNumero = true;
			}

			if (tieneLetra && tieneNumero) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Metodo que verifica que el mensaje no sea nulo, no esté vacío, no contenga
	 * solo espacios y tenga máximo 500 caracteres
	 * 
	 * @param mensaje texto que escribe el usuario
	 * @return true si es valido, false si no lo es
	 */
	public boolean esMensajeValido(String mensaje) {
		if (mensaje == null || mensaje.trim().isEmpty() || mensaje.length() > 500) {
			return false;
		}
		return true;
	}

	/**
	 * Formatea la fecha
	 * 
	 * @param l Fecha introducida
	 * @return Devuele la fecha con el formato deseado
	 */
	public String formatoFecha(LocalDateTime l) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
		String fechaHoraFormateada = l.format(formatter);
		return fechaHoraFormateada;
	}

	public LocalDateTime convertirFechaFin(String fecha) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {
			// Intentar parsear la fecha sin hora
			LocalDate fechaLocal = LocalDate.parse(fecha, formato);

			// Obtener la hora actual y restarle 1 hora
			LocalTime horaActual = LocalTime.now();

			// Convertir la fecha a LocalDateTime con la hora establecida a medianoche
			// (00:00:00)
			return LocalDateTime.of(fechaLocal, horaActual);

		} catch (DateTimeParseException e) {
			return null; // Retorna null si la fecha no es válida
		}
	}

	public LocalDateTime convertirFechaInicio(String fecha) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {
			// Intentar parsear la fecha sin hora
			LocalDate fechaLocal = LocalDate.parse(fecha, formato);

			// Convertir la fecha a LocalDateTime con la hora establecida a medianoche
			// (00:00:00)
			return fechaLocal.atStartOfDay();

		} catch (DateTimeParseException e) {
			return null; // Retorna null si la fecha no es válida
		}
	}

	public LocalDate convertirFechaNac(String fechaNac) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(fechaNac, formatter);
	}

	public boolean comprobarDniNie(String documento) {
		// Expresión regular para DNI (8 números + 1 letra)
		String dniPattern = "^[0-9]{8}[A-HJ-NP-TV-Z]$";

		// Expresión regular para NIE (X, Y o Z + 7 números + 1 letra)
		String niePattern = "^[XYZ][0-9]{7}[A-HJ-NP-TV-Z]$";

		// Compilar patrones y validar
		return Pattern.matches(dniPattern, documento) || Pattern.matches(niePattern, documento);
	}
	
	public boolean verificarTelefono(String texto) {
        return texto != null && texto.matches("\\d+");
    }

}
