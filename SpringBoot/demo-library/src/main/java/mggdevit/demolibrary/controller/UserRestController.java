package mggdevit.demolibrary.controller;

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

import mggdevit.demolibrary.entity.User;
import mggdevit.demolibrary.repo.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@GetMapping
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<User> getUserById(@PathVariable Long id) {
		return userRepository.findById(id);
	}

	@PostMapping
	User postUser(@Valid @RequestBody User user) {
		logger.info("postUser");
		return userRepository.save(user);
	}

	@PutMapping("/{id}")
	ResponseEntity<User> putUser(@PathVariable Long id, @Valid @RequestBody User user) {
		return (!userRepository.existsById(id)) ? new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED)
				: new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	void deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
	}

}
