package mggdevit.pizzauno.repo;

import org.springframework.data.repository.CrudRepository;

import mggdevit.pizzauno.entity.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {

}
