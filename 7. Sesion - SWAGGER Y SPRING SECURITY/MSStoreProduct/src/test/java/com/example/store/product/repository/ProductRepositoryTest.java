package com.example.store.product.repository;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.store.product.bean.Category;
import com.example.store.product.bean.Product;

//@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@RunWith(SpringRunner.class)
public class ProductRepositoryTest {

	// @Mock
//	@Autowired
//	private ProductRepository productRepository;

//	@Test
//	public void findByCateroryReturnProducts() {
//		Product product01 = Product.builder().name("computer").category(Category.builder().id(1L).build())
//				.description("").stock(Double.parseDouble("10")).price(Double.parseDouble("1240.99")).status("Created")
//				.createAt(new Date()).build();
//		productRepository.save(product01);

//		List<Product> founds = productRepository.findByCategory(Category.builder().id(1L).build());
//		System.out.println(founds.toString());
//		Assertions.assertThat(founds.size()).isEqualTo(3);
//	}
}
