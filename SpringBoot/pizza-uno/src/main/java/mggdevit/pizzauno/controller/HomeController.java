package mggdevit.pizzauno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mggdevit.pizzauno.repo.PizzaRepository;

@Controller
public class HomeController {

	@Autowired
	private PizzaRepository pizzaRepository;

	Logger logger = LoggerFactory.getLogger(HomeController.class);

//	@RequestMapping("/")
//	@GetMapping("/")
//	public String index() {
//		return ("index");
//	}

	@GetMapping("/")
	public String index(Model model) {
		logger.info("get index called");
		model.addAttribute("pizzas", pizzaRepository.findAll());
		return ("index");
	}

}
