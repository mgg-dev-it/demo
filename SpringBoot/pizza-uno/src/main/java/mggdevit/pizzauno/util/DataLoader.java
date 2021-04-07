package mggdevit.pizzauno.util;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import mggdevit.pizzauno.entity.Pizza;
import mggdevit.pizzauno.repo.PizzaRepository;

@Component
public class DataLoader {

	private final PizzaRepository pizzaRepository;

	public DataLoader(PizzaRepository pizzaRepository) {
		this.pizzaRepository = pizzaRepository;
	}

	@PostConstruct
	private void loadData() {
		pizzaRepository
				.saveAll(List.of(new Pizza("Margherita", 100), new Pizza("Salame", 300), new Pizza("Prosciutto", 300)));
	}
}
