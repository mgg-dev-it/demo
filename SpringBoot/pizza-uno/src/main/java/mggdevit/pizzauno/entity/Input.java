package mggdevit.pizzauno.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Input {

	@Min(value = 500, message = "must be equal or greater than 500")
	@Max(value = 2000, message = "must be equal or less than 2000")
	private int budget;

	public Input() {
	}

	public Input(int budget) {
		this.budget = budget;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

}
