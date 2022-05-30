package com.example.store.customer.jdbc;

import java.util.List;

import com.example.store.customer.bean.Customer;

public interface CustomerDao {
	public List<Customer> findAllCustomer();

	public Customer insertCustomer(Customer customer);

}