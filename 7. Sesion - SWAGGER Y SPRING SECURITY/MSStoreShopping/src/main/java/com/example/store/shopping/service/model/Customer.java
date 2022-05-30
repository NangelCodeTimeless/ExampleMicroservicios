package com.example.store.shopping.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
	private Long id;

	private String numberID;

	private String firstName;

	private String lastName;

	private String email;

	private String photoUrl;

	private Region region;

	private String state;
}
