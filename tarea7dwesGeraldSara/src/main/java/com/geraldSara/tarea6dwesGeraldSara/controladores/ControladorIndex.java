package com.geraldSara.tarea6dwesGeraldSara.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorIndex {

	@GetMapping("/")
	public String index() {
		return "index";
	}

}
