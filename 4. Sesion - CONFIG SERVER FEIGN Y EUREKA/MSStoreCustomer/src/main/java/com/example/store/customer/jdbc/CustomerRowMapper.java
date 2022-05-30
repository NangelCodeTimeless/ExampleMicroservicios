package com.example.store.customer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.store.customer.bean.Customer;
import com.example.store.customer.bean.Region;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setId(rs.getLong("id"));
		customer.setNumberID(rs.getString("number_id"));
		customer.setEmail(rs.getString("email"));
		customer.setPhotoUrl(rs.getString("photo_url"));
		customer.setFirstName(rs.getString("first_name"));
		customer.setLastName(rs.getString("last_name"));
		Region region = new Region();
		region.setId(rs.getLong("id"));
		region.setName(rs.getString("name"));
		customer.setRegion(region);
		customer.setState(rs.getString("state"));
		return customer;
	}
}
