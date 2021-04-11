package mggdevit.pizzadue.service;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import mggdevit.pizzadue.dao.PizzaDAOInterface;
import mggdevit.pizzadue.entity.Pizza;
import mggdevit.pizzadue.entity.PizzaRowMapper;

@Service
public class PizzaService implements PizzaServiceInterface {

	@Autowired
//	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PizzaDAOInterface pizzaDAO;

//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}

	@Override
	public List<Pizza> getAllItems() {
		return pizzaDAO.getAllItems();
	}

	@Override
	public Optional<Pizza> findById(long id) {
		return Optional.of(jdbcTemplate.queryForObject("select * from pizza where id=?", new PizzaRowMapper(), id));
	}

	@Override
	public boolean existsById(long id) {
		Pizza pizza = jdbcTemplate.queryForObject("select * from pizza where id=?", new PizzaRowMapper(), id);
		return pizza != null;
	}

	@Override
	public Pizza addPizza(Pizza pizza) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("insert into pizza(name, value) values (?,?)",
						new String[] { "id" });
				ps.setString(1, pizza.getName());
				ps.setInt(2, pizza.getValue());
				return ps;
			}
		}, keyHolder);

		BigInteger bi = keyHolder.getKeyAs(BigInteger.class);
		pizza.setId(bi.longValue());
		return pizza;
	}

	@Override
	public Pizza updatePizza(Pizza pizza) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("update pizza set name=?, value=? where id=?");
				ps.setString(1, pizza.getName());
				ps.setInt(2, pizza.getValue());
				ps.setLong(3, pizza.getId());
				return ps;
			}
		});
		return pizza;
	}

	@Override
	public void deletePizza(Long id) {
		jdbcTemplate.update("delete from pizza where id=" + id);
	}
}
