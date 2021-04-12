package mggdevit.pizzadue.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mggdevit.pizzadue.entity.Pizza;
import mggdevit.pizzadue.entity.PizzaList;
import mggdevit.pizzadue.service.PizzaServiceInterface;

@Component
public class Calculator {

	@Autowired
	//private PizzaRepository pizzaRepository;
	private PizzaServiceInterface pizzaService;

	@Autowired
	SmallestRemainder sr;

	public ArrayList<PizzaList> getCalc(int budget) {

		ArrayList<Integer> alp = new ArrayList<>();
		pizzaService.getAllItems().forEach((Pizza p) -> alp.add(p.getValue()));
		int[] values = new ArrayList<>(alp).stream().mapToInt(i -> i).toArray();
		sr.reset(budget, values);
		sr.findTheSmallestRemainder();

		ArrayList<ArrayList<Integer>> alResultList = sr.getResult();

		ArrayList<PizzaList> pll = new ArrayList<PizzaList>();
		if (alResultList.size() < 1)
			return pll;

		HashMap<Integer, String> hmPizzaByPrice = new HashMap<>();
		pizzaService.getAllItems().forEach((Pizza p) -> {
			hmPizzaByPrice.put(p.getValue(), p.getName());
		});

		for (int j = 0; j < 5 && j < alResultList.size(); j++) {
			ArrayList<Integer> al = alResultList.get(j);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < al.size() - 1; i++) {
				if (i > 0)
					sb.append(", ");
				sb.append(hmPizzaByPrice.get(al.get(i)));
			}
			pll.add(new PizzaList(sb.toString(), al.get(al.size() - 1)));
		}

		return pll;
	}
}
