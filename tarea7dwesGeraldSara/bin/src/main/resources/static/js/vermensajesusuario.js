document.getElementById('searchInput').addEventListener('input', function() {
	var filter = this.value.toLowerCase();
	var rows = document.querySelectorAll('#usuariosTable tbody tr');

	rows.forEach(function(row) {
		var nombreUsuario = row.querySelector('.nombre-usuario').textContent.toLowerCase();
		if (nombreUsuario.indexOf(filter) > -1) {
			row.style.display = '';
		} else {
			row.style.display = 'none';
		}
	});
});