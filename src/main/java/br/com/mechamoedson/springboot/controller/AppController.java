package br.com.mechamoedson.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title","CRUD app usando Spring Boot, AngularJS, Spring Data, JPA/Hibernate e MySQL");
		return "index";
	}

	@RequestMapping("/partials/{pagina}")
	String partialHandler(@PathVariable("pagina") final String page) {
		return page;
	}

}
