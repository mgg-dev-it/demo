package mggdevit.pizzadue.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PizzaRowMapper implements RowMapper<Pizza> {

	@Override
	public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pizza pizza = new Pizza();
		pizza.setId(rs.getLong("id"));
		pizza.setName(rs.getString("name"));
		pizza.setValue(rs.getInt("value"));
		return pizza;
	}

}
