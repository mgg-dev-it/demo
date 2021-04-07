package mggdevit.pizzauno.controller;

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

import mggdevit.pizzauno.entity.Pizza;
import mggdevit.pizzauno.repo.PizzaRepository;

@RestController
@RequestMapping("/api/users")public class PizzaRestController {

	@Autowired
	private PizzaRepository pizzaRepository;

	Logger logger = LoggerFactory.getLogger(PizzaRestController.class);

	@GetMapping
	public Iterable<Pizza> getPizzas() {
		return pizzaRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<Pizza> getPizzaById(@PathVariable Long id) {
		return pizzaRepository.findById(id);
	}

	@PostMapping
	Pizza postPizza(@Valid @RequestBody Pizza pizza) {
		logger.info("postUser");
		return pizzaRepository.save(pizza);
	}

	@PutMapping("/{id}")
	ResponseEntity<Pizza> puPizzar(@PathVariable Long id, @Valid @RequestBody Pizza pizza) {
		return (!pizzaRepository.existsById(id)) ? new ResponseEntity<>(pizzaRepository.save(pizza), HttpStatus.CREATED)
				: new ResponseEntity<>(pizzaRepository.save(pizza), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	void deletePizza(@PathVariable Long id) {
		pizzaRepository.deleteById(id);
	}

}
