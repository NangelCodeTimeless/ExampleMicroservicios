package com.example.store.shopping.service.client;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.store.shopping.service.model.Customer;

//@Component
@FeignClient(name = "CONFIG-CUSTOMER", fallback = CustomerHystrixFallbackFactory.class)
//@RequestMapping("/customers")
public interface CustomerClient {

	@GetMapping(value = "/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);

	static class HystrixClientFallbackFactory implements FallbackFactory<CustomerClient> {
		@Override
		public CustomerClient create(Throwable cause) {
			return new CustomerClient() {
				@Override
				public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
					Customer customer = Customer.builder().firstName("none").lastName("none").email("none")
							.photoUrl("none").build();
					return ResponseEntity.ok(customer);
				}
			};
		}
	}
}
