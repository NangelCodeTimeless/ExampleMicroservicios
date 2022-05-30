package com.example.store.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.store.product.bean.Users;
import com.example.store.product.repository.UsersRepository;

@Service
public class InBdUserDetailsService implements UserDetailsService {

	@Autowired
	UsersRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = repository.findByUsernameAndType(username, "product");
		List<GrantedAuthority> roles = new ArrayList<>();// para obtener una authority para autorizar/controlar un acceso.
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		UserDetails userDetails = new User(users.getUsername(), users.getPassword(), roles);
		return userDetails;
	}
}
