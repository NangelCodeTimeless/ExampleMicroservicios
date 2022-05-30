package com.example.store.product.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tbl_users")
public class Users {

	@Id
	private Long id;
	
	private String username;
	
	private String password;
	
	private String type;
}
