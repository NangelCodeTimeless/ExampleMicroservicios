package com.example.store.customer.jdbc;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.store.customer.bean.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	NamedParameterJdbcTemplate template;

	@Override
	public List<Customer> findAllCustomer() {
		// TODO Auto-generated method stub
		final String sql = "SELECT c.id, c.number_id, c.email, c.photo_url, c.first_name, c.last_name, c.state, re.id,re.name\r\n"
				+ "	FROM public.tbl_customers c inner join public.tbl_regions re ON c.region_id = re.id";
		return template.query(sql, new CustomerRowMapper());
	}
	 final String sql = "insert into employee(employeeId, employeeName , employeeAddress,employeeEmail) values(:employeeId,:employeeName,:employeeEmail,:employeeAddress)";

	@Override
	public Customer insertCustomer(Customer customer) {
		final String sql = "INSERT INTO public.tbl_customers(\r\n"
				+ "	number_id, email, photo_url, region_id, first_name, last_name, state)\r\n"
				+ "	VALUES (:number_id, :email, :photo_url, :region_id, :first_name, :last_name, :state);";
		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("number_id", customer.getNumberID())
				.addValue("email", customer.getEmail())
				.addValue("photo_url", customer.getPhotoUrl())
				.addValue("region_id", customer.getRegion().getId())
				.addValue("first_name", customer.getFirstName())
				.addValue("last_name", customer.getLastName())
				.addValue("state", "CREATED");
		int insert = template.update(sql, param, holder);
		if (insert == 0) {
			return new Customer();
		}
		AtomicLong count = new AtomicLong(1);
		customer.setId(count.get());
		return customer;
	}

}
