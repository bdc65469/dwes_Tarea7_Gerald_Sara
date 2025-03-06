
    // Función para filtrar las filas de la tabla según cliente y estado
    function filterTable() {
        const searchFilter = document.getElementById('searchInput').value.toLowerCase();
        const checkboxes = document.querySelectorAll('.checkbox-group input[type="checkbox"]');
        const selectedStates = [];

        // Recoger los estados seleccionados (esto solo afectará a las filas cuando cambien los checkboxes)
        checkboxes.forEach(checkbox => {
            if (checkbox.checked) {
                selectedStates.push(checkbox.value);
            }
        });

        const rows = document.querySelectorAll('#plantasTable tbody tr');

        rows.forEach(function(row) {
            const estadoCell = row.querySelector('.estado');
            const clienteCell = row.querySelector('.cliente');  // Buscamos por cliente

            let showRow = true;

            // Filtrar por cliente (solo se activa cuando hay un texto en el input)
            if (searchFilter) {
                const cliente = clienteCell.textContent.toLowerCase();
                if (cliente.indexOf(searchFilter) === -1) {
                    showRow = false;  // No coincidirá con el cliente
                }
            }

            // Filtrar por estado
            if (estadoCell) {
                const estado = estadoCell.textContent.trim();
                if (selectedStates.length > 0 && !selectedStates.includes(estado)) {
                    showRow = false;  // No coincidirá con el estado
                }
            }

            // Mostrar u ocultar la fila según los filtros
            if (showRow) {
                row.style.display = '';  // Mostrar la fila
            } else {
                row.style.display = 'none';  // Ocultar la fila
            }
        });
    }

    // Escuchar los cambios en el campo de búsqueda (input de cliente)
    document.getElementById('searchInput').addEventListener('input', filterTable);

    // Escuchar los cambios en los checkboxes para filtrar la tabla por estado
    document.querySelectorAll('.checkbox-group input[type="checkbox"]').forEach(checkbox => {
        checkbox.addEventListener('change', filterTable);
    });

    // Llamar a la función inicialmente para aplicar cualquier filtro al cargar la página
    window.onload = filterTable;

