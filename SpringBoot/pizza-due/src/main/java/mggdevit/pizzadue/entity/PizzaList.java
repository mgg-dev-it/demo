package mggdevit.pizzadue.entity;

public class PizzaList {

	private String pizzas;
	private int remainder;

	public PizzaList(String pizzas, int remainder) {
		this.pizzas = pizzas;
		this.remainder = remainder;
	}

	public String getPizzas() {
		return pizzas;
	}

	public int getRemainder() {
		return remainder;
	}

}
