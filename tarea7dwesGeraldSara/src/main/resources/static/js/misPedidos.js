
    function filterTable() {
        const checkboxes = document.querySelectorAll('.checkbox-group input[type="checkbox"]');
        const selectedStates = [];

        // Recoger los estados seleccionados
        checkboxes.forEach(checkbox => {
            if (checkbox.checked) {
                selectedStates.push(checkbox.value.toLowerCase().trim()); // Convertimos a minúsculas
            }
        });

        const rows = document.querySelectorAll('#tablaPedidos tbody tr');

        rows.forEach(function(row) {
            const estadoCell = row.querySelector('.estado');

            if (estadoCell) {
                const estado = estadoCell.textContent.toLowerCase().trim(); // Convertimos a minúsculas

                // Mostrar todas las filas si no hay filtros seleccionados
                if (selectedStates.length === 0 || selectedStates.includes(estado)) {
                    row.style.display = '';  // Mostrar la fila
                } else {
                    row.style.display = 'none';  // Ocultar la fila
                }
            }
        });
    }

    // Escuchar los cambios en los checkboxes
    document.querySelectorAll('.checkbox-group input[type="checkbox"]').forEach(checkbox => {
        checkbox.addEventListener('change', filterTable);
    });

    // Aplicar el filtro al cargar la página
    window.onload = filterTable;
