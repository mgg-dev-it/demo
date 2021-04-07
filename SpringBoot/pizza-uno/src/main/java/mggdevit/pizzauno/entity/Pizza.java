package mggdevit.pizzauno.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pizza")
public class Pizza {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false, length = 255)
	@Size(min = 5, message = "required, must be at least 5 characters length")
	private String name;

	@Column(nullable = false)
	@Min(value = 100, message = "must be equal or greater than 100")
	@Max(value = 500, message = "must be equal or less than 500")
	private int value;

	public Pizza(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
