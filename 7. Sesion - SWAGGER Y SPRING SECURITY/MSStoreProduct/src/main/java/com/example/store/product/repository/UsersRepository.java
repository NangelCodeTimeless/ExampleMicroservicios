package com.example.store.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.store.product.bean.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	Users findByUsernameAndType(String username, String type);
}
