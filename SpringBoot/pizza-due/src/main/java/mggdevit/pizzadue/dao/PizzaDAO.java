package mggdevit.pizzadue.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mggdevit.pizzadue.entity.Pizza;
import mggdevit.pizzadue.entity.PizzaRowMapper;

@Repository
public class PizzaDAO implements PizzaDAOInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Pizza> getAllItems() {
		String sql = "select id, name, value from pizza p order by id";

		RowMapper<Pizza> rowMapper = new PizzaRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

}
