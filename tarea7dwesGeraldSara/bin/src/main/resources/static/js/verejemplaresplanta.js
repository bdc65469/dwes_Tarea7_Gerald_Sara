document.addEventListener("DOMContentLoaded", function() {
	const checkboxes = document.querySelectorAll('input[name="plantasSeleccionadas"]');
	const botonVerEjemplares = document.getElementById("verEjemplaresBtn");

	// Deshabilitar el botón inicialmente
	botonVerEjemplares.disabled = true;

	// Añadir evento de cambio a los checkboxes
	checkboxes.forEach(checkbox => {
		checkbox.addEventListener("change", function() {
			// Comprobar si al menos un checkbox está seleccionado
			const seleccionados = document.querySelectorAll('input[name="plantasSeleccionadas"]:checked');
			botonVerEjemplares.disabled = seleccionados.length === 0; // Deshabilitar si no hay checkbox seleccionado
		});
	});
});

// Script para filtrar la tabla por nombre común
document.getElementById('searchInput').addEventListener('input', function() {
	var filter = this.value.toLowerCase();
	var rows = document.querySelectorAll('#plantasTable tbody tr');

	rows.forEach(function(row) {
		var nombreComun = row.querySelector('.nombre-comun').textContent.toLowerCase();
		if (nombreComun.indexOf(filter) > -1) {
			row.style.display = '';
		} else {
			row.style.display = 'none';
		}
	});
});