package mggdevit.pizzadue.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mggdevit.pizzadue.entity.Input;
import mggdevit.pizzadue.service.PizzaServiceInterface;
import mggdevit.pizzadue.util.Calculator;;

@Controller
public class HomeController {

	@Autowired
	private PizzaServiceInterface pizzaService;

	@Autowired
	private Calculator calculator;

	Logger logger = LoggerFactory.getLogger(HomeController.class);

	@GetMapping("/")
	public String indexGet(Model model) {
		logger.info("get index called");
		model.addAttribute("pizzas", pizzaService.getAllItems());
		Input input = new Input(1000);
		model.addAttribute("input", input);
		model.addAttribute("calclist", calculator.getCalc(input.getBudget()));
		return ("index");
	}

	@PostMapping("/")
	public String indexPost(Model model, @ModelAttribute @Valid Input input, Errors errors) {
		logger.info("post index called");
		logger.info("budget = " + input.getBudget());
		if (errors.hasErrors()) {
			logger.info("error = " + errors.toString());
		}
		model.addAttribute("pizzas", pizzaService.getAllItems());
		model.addAttribute("input", input);
		model.addAttribute("calclist", calculator.getCalc(errors.hasErrors() ? 0 : input.getBudget()));
		return ("index");
	}

}
