window.addEventListener("beforeunload", function (event) {
	    navigator.sendBeacon("/logout");
	});