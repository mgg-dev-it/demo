package mggdevit.demolibrary.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mggdevit.demolibrary.entity.Role;
import mggdevit.demolibrary.repo.RoleRepository;

@RestController
@RequestMapping("/roles")
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

}
