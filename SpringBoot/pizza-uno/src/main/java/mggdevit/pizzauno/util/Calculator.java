package mggdevit.pizzauno.util;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mggdevit.pizzauno.controller.HomeController;
import mggdevit.pizzauno.entity.Pizza;
import mggdevit.pizzauno.entity.PizzaList;
import mggdevit.pizzauno.repo.PizzaRepository;

@Component
public class Calculator {

	Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private PizzaRepository pizzaRepository;

	@Autowired
	SmallestRemainder sr;

	// public List<Pizza> getCalc(int budget) {
	public ArrayList<PizzaList> getCalc(int budget) {

		ArrayList<Integer> alp = new ArrayList<>();
		pizzaRepository.findAll().forEach((Pizza p) -> alp.add(p.getValue()));
		int[] values = new ArrayList<>(alp).stream().mapToInt(i -> i).toArray();
		sr.reset(budget, values);
		sr.findTheSmallestRemainder();

		ArrayList<ArrayList<Integer>> alResultList = sr.getResult();

		// logger.info("sr.getResult().size() = " + sr.getResult().size());

		// ArrayList<ArrayList<String>> alToTheScreen = new ArrayList<>();
		ArrayList<PizzaList> pl = new ArrayList<PizzaList>();
		if (alResultList.size() < 1)
			return pl;

		alResultList.forEach((ArrayList<Integer> al) -> {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < al.size() - 1; i++) {
				if (i > 0)
					sb.append(", ");
				sb.append(al.get(i).toString());
			}
			// alLine.add(sb.toString());
			// alLine.add(al.get(al.size() - 1).toString());
			if (pl.size() < 5)
				pl.add(new PizzaList(sb.toString(), al.get(al.size() - 1)));
		});
		return pl;

		// return List.of(new Pizza("a", 100), new Pizza("b", 300), new Pizza("c",
		// budget));

		// if(budget==0) return new ArrayList<Pizza>();
		// return List.of(new Pizza("a", 100), new Pizza("b", 300), new Pizza("c",
		// budget));
	}
}
