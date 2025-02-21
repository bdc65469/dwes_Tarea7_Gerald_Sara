function setSelectedOption(option) {
	var button = document.getElementById('dropdownMenuButton');
	button.innerHTML = option.innerHTML; // Muestra solo el nombre común en el botón

	// Guardar datos en el dataset del botón
	button.dataset.codigo = option.dataset.codigo;
	button.dataset.nombreCientifico = option.dataset.nombrecientifico;
}

document
	.getElementById('modificar')
	.addEventListener(
		'click',
		function() {
			var button = document
				.getElementById('dropdownMenuButton');

			// Obtener valores
			var codigo = button.dataset.codigo || '';
			var nombreComun = button.innerHTML; // El botón ya tiene el nombre común
			var nombreCientifico = button.dataset.nombreCientifico
				|| '';

			// Asignar valores al formulario
			document.getElementById('codigo').value = codigo;
			document.getElementById('nombreComun').value = nombreComun;
			document.getElementById('nombreCientifico').value = nombreCientifico;
		});