package mggdevit.demolibrary.util;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import mggdevit.demolibrary.entity.User;
import mggdevit.demolibrary.entity.Role;
import mggdevit.demolibrary.repo.RoleRepository;
import mggdevit.demolibrary.repo.UserRepository;

@Component
public class DataLoader {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public DataLoader(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@PostConstruct
	private void loadData() {
		userRepository.saveAll(List.of(new User("user1"), new User("user2"), new User("user3"), new User("user4")));
		roleRepository.saveAll(List.of(new Role("ADMIN"), new Role("LIBRARIAN"), new Role("MEMBER"), new Role("USER")));
	}
}
