package com.tmb.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

	private long id;
	private String name;
	private String phone;	
	private String address;	
	
}
