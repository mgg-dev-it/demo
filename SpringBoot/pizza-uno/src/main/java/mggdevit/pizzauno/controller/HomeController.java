package mggdevit.pizzauno.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mggdevit.pizzauno.entity.Input;
import mggdevit.pizzauno.repo.PizzaRepository;
import mggdevit.pizzauno.util.Calculator;

@Controller
public class HomeController {

	@Autowired
	private PizzaRepository pizzaRepository;

	@Autowired
	private Calculator calculator;

	Logger logger = LoggerFactory.getLogger(HomeController.class);

//	@RequestMapping("/")
//	@GetMapping("/")
//	public String index() {
//		return ("index");
//	}

	@GetMapping("/")
	public String indexGet(Model model) {
		logger.info("get index called");
		model.addAttribute("pizzas", pizzaRepository.findAll());
		Input input = new Input(1000);
		model.addAttribute("input", input);
		model.addAttribute("calclist", calculator.getCalc(input.getBudget()));
		return ("index");
	}

	@PostMapping("/")
	public String indexPost(Model model, @ModelAttribute @Valid Input input) {
		logger.info("post index called");
		logger.info("budget = " + input.getBudget());
		model.addAttribute("pizzas", pizzaRepository.findAll());
		model.addAttribute("input", input);
		model.addAttribute("calclist", calculator.getCalc(input.getBudget()));
		return ("index");
	}

}
