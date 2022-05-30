package com.example.store.shopping.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.store.shopping.service.model.Product;

@FeignClient(name = "CONFIG-PRODUCT", fallback = ProductHystrixFallbackFactory.class)
//@RequestMapping(value = "/products")
public interface ProductClient {

	@GetMapping(value = "/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id);

	@PutMapping(value = "/products/{id}/stock")
	public ResponseEntity<Product> updateStockProduct(@PathVariable Long id,
			@RequestParam(name = "quantity", required = true) Double quantity);
}