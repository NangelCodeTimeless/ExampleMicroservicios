package com.example.store.product;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.store.product.bean.Users;
import com.example.store.product.repository.UsersRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource( locations = "classpath:bootstrap.yml")
class MsStoreProductApplicationTests {
	@Autowired
	private UsersRepository repository;

	@Autowired
	private BCryptPasswordEncoder encoder; // que utiliza la función de hash fuerte de BCrypt.

	@Test
	void creatUserTest() {
		Users users = new Users();
		users.setId(2L);
		users.setUsername("Jean");
		users.setPassword(encoder.encode("1234"));
		users.setType("product");
		Users check = repository.save(users);
		assertTrue(check.getUsername().equals(users.getUsername()));

	}
}
