package com.example.store.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.store.customer.bean.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	Users findByUsernameAndType(String username, String type);
}
