//package com.example.store.customer.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class InMemoryUserDetailsService implements UserDetailsService {
//	private final List<UserDetails> users = List.of(
//			User.builder().username("jean").password("123").authorities("READ").build(),
//			User.builder().username("admin").password("admin").authorities("ADMIN").build());
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<UserDetails> user = users.stream().filter(userDetails -> userDetails.getUsername().equals(username))
//				.findFirst();
//		if (user.isEmpty())
//			throw new UsernameNotFoundException("User " + username + " not found");
//		return user.get();
//	}
//
//}
