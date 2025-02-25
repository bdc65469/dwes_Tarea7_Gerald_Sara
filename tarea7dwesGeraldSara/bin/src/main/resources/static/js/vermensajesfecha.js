$(function() {
	// Inicializar datepickers
	$("#fechaInicial").datepicker({
		dateFormat: "dd/mm/yy",
		showButtonPanel: false,
		changeMonth: true,
		changeYear: true,
		showAnim: "slideDown",
	});

	$("#fechaFinal").datepicker({
		dateFormat: "dd/mm/yy",
		showButtonPanel: false,
		changeMonth: true,
		changeYear: true,
		showAnim: "slideDown",
	});

});