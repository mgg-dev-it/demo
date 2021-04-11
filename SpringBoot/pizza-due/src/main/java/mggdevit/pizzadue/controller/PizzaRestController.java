package mggdevit.pizzadue.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mggdevit.pizzadue.entity.Pizza;
import mggdevit.pizzadue.service.PizzaServiceInterface;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {

	@Autowired
	private PizzaServiceInterface pizzaService;

	Logger logger = LoggerFactory.getLogger(PizzaRestController.class);

	@GetMapping
	public Iterable<Pizza> getPizzas() {
		logger.info("getPizzas");
		return pizzaService.getAllItems();
	}

	@GetMapping("/{id}")
	Optional<Pizza> getPizzaById(@PathVariable Long id) {
		logger.info("getPizzaById " + id);
		return pizzaService.findById(id);
	}

	@PostMapping
	Pizza postPizza(@Valid @RequestBody Pizza pizza) {
		logger.info("postPizza");
		return pizzaService.addPizza(pizza);
	}

	@PutMapping("/{id}")
	ResponseEntity<Pizza> putPizza(@PathVariable Long id, @Valid @RequestBody Pizza pizza) {
		logger.info("putPizza " + id);
		return (!pizzaService.existsById(id)) ? new ResponseEntity<>(pizzaService.addPizza(pizza), HttpStatus.CREATED)
				: new ResponseEntity<>(pizzaService.updatePizza(pizza), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	void deletePizza(@PathVariable Long id) {
		logger.info("deletePizza " + id);
		pizzaService.deletePizza(id);
	}

}
