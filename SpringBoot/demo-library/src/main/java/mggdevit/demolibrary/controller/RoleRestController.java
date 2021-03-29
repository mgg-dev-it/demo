package mggdevit.demolibrary.controller;

import java.util.Optional;

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

import mggdevit.demolibrary.entity.Role;
import mggdevit.demolibrary.repo.RoleRepository;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

	@Autowired
	private RoleRepository roleRepository;

	@GetMapping
	public Iterable<Role> getRoles() {
		return roleRepository.findAll();
	}

	@GetMapping("/{id}")
	Optional<Role> geRoleById(@PathVariable Long id) {
		return roleRepository.findById(id);
	}

	@PostMapping
	Role postRole(@RequestBody Role role) {
		return roleRepository.save(role);
	}

	@PutMapping("/{id}")
	ResponseEntity<Role> putRole(@PathVariable Long id, @RequestBody Role user) {
		return (!roleRepository.existsById(id)) ? new ResponseEntity<>(roleRepository.save(user), HttpStatus.CREATED)
				: new ResponseEntity<>(roleRepository.save(user), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	void deleteRole(@PathVariable Long id) {
		roleRepository.deleteById(id);
	}

}
