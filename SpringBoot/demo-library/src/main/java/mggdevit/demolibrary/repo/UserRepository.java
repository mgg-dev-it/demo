package mggdevit.demolibrary.repo;

import org.springframework.data.repository.CrudRepository;

import mggdevit.demolibrary.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
