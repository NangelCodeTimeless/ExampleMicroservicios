package com.example.store.shopping.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.store.shopping.service.model.Product;

@Component
public class ProductHystrixFallbackFactory implements ProductClient {

	@Override
	public ResponseEntity<Product> getProduct(Long id) {
		// TODO Auto-generated method stub
		return  ResponseEntity.ok(new Product());
	}

	@Override
	public ResponseEntity<Product> updateStockProduct(Long id, Double quantity) {
		// TODO Auto-generated method stub
		return null;
	}

}
