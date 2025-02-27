
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

		document.addEventListener("DOMContentLoaded", function() {
			let modificarBtn = document.getElementById("modificar");
			let dropdownItems = document.querySelectorAll(".dropdown-item");
			let editarBtn = document.querySelector(".btn-register"); // Botón "EDITAR PLANTA"

			// Deshabilita el botón al inicio
			modificarBtn.disabled = true;
			editarBtn.disabled = true;

			dropdownItems.forEach(item => {
				item.addEventListener("click", function() {
					// Habilita el botón cuando se selecciona una opción
					modificarBtn.disabled = false;
					
					modificarBtn.addEventListener("click", function(){
						editarBtn.disabled = false;
					})
					
				});
			});
		});