package mggdevit.demolibrary.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mggdevit.demolibrary.entity.User;
import mggdevit.demolibrary.repo.UserRepository;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<User> geUserById(@PathVariable Long id) {
		return userRepository.findById(id);
	}

	/*
	 * @GetMapping Iterable<Coffee> getCoffees() { return coffees; }
	 * 
	 * @GetMapping("/{id}") Optional<Coffee> getCoffeeById(@PathVariable String id)
	 * { for (Coffee c: coffees) { if (c.getId().equals(id)) { return
	 * Optional.of(c); } } return Optional.empty(); }
	 * 
	 * @PostMapping Coffee postCoffee(@RequestBody Coffee coffee) {
	 * coffees.add(coffee); return coffee; }
	 * 
	 * @PutMapping("/{id}") Coffee putCoffee(@PathVariable String id, @RequestBody
	 * Coffee coffee) { int coffeeIndex = -1; for (Coffee c: coffees) { if
	 * (c.getId().equals(id)) { coffeeIndex = coffees.indexOf(c);
	 * coffees.set(coffeeIndex, coffee); } } return (coffeeIndex == -1) ?
	 * postCoffee(coffee) : coffee; }
	 * 
	 * @DeleteMapping("/{id}") void deleteCoffee(@PathVariable String id) {
	 * coffees.removeIf(c -> c.getId().equals(id)); }
	 */

}
