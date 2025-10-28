package com.tmb.model.validators;

import com.tmb.dto.CustomerRegisterDto;
import com.tmb.dto.CustomerUpdateDto;

public class CustomerValidator {

	public void validate(CustomerRegisterDto customerRegisterDto) {
		if (customerRegisterDto == null) {
			throw new IllegalArgumentException("Os dados do Cliente não podem ser nulos.");
		}
		
		String name = customerRegisterDto.name();
		if (name == null || name.isBlank() || name.length() < 3) {
			throw new IllegalArgumentException("O nome do cliente é invalido: " + name);
		}
	}
	
	public void validate(CustomerUpdateDto customerUpdateDto) {
		if (customerUpdateDto == null) {
			throw new IllegalArgumentException("Os dados do Cliente não podem ser nulos.");
		}
				
		long id = customerUpdateDto.id();
		if (id < 1) {
			throw new IllegalArgumentException("O id do cliente é invalido.");
		}
		
		String name = customerUpdateDto.name();
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
