package com.tmb.model.utils;

import com.tmb.model.dto.CustomerRegisterDto;
import com.tmb.model.dto.CustomerUpdateDto;

public class CustomerValidator {

	public void validate(CustomerRegisterDto customerRegisterDto) {
		String name = customerRegisterDto.name();
		
		if (name == null || name.isBlank() || name.length() < 3) {
			throw new IllegalArgumentException("O nome do cliente é invalido: " + name);
		}
	}
	
	public void validate(CustomerUpdateDto customerUpdateDto) {
		long id = customerUpdateDto.id();
		String name = customerUpdateDto.name();
		
		if (id < 1) {
			throw new IllegalArgumentException("O id do cliente é invalido.");
		}
		
		if (name == null || name.isBlank() || name.length() < 3) {
			throw new IllegalArgumentException("O nome do cliente é invalido: " + name);
		}
	}
	
	public void validate(long id) {
		if (id < 1) {
			throw new IllegalArgumentException("O id do cliente é invalido.");
		}
	}

}
