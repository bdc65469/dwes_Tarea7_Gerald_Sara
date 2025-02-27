document
	.getElementById('searchInput')
	.addEventListener(
		'input',
		function() {
			var filter = this.value.toLowerCase();
			var rows = document
				.querySelectorAll('#plantasTable tbody tr');

			rows
				.forEach(function(row) {
					var nombreComun = row
						.querySelector('.nombre-comun').textContent
						.toLowerCase();
					if (nombreComun.indexOf(filter) > -1) {
						row.style.display = '';
					} else {
						row.style.display = 'none';
					}
				});
		});
		

