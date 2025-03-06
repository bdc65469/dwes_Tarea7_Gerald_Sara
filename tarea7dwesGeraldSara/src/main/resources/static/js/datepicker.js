$(function() {
	// Inicializar datepickers
	$("#fechaNac").datepicker({
		dateFormat : "dd/mm/yy",
		showButtonPanel : false,
		changeMonth : true,
		changeYear : true,
		showAnim : "slideDown",
		yearRange : "1900:2025"
	});

});