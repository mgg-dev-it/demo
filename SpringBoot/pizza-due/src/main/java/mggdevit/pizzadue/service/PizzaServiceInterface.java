package mggdevit.pizzadue.service;

import java.util.List;
import java.util.Optional;

import mggdevit.pizzadue.entity.Pizza;

public interface PizzaServiceInterface {

	List<Pizza> getAllItems();

    public Optional<Pizza> findById(long id);

    public boolean existsById(long id);
    
    Pizza addPizza(Pizza pizza);

    Pizza updatePizza(Pizza pizza);

	void deletePizza(Long id);
}
