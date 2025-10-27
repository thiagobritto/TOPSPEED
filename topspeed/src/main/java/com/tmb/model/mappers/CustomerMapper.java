package com.tmb.model.mappers;

import com.tmb.model.dto.CustomerResponseDto;
import com.tmb.model.dto.CustomerRegisterDto;
import com.tmb.model.dto.CustomerUpdateDto;
import com.tmb.model.entities.Customer;
import com.tmb.model.utils.StringUtils;

public class CustomerMapper {

	private CustomerMapper() {
		// static
	}

	public static Customer toEntity(CustomerRegisterDto customerRegisterDto) {
		return new Customer(0, 
				customerRegisterDto.name(), 
				customerRegisterDto.phone(), 
				customerRegisterDto.address());
	}

	public static Customer toEntity(CustomerUpdateDto customerUpdateDto) {
		return new Customer(
				customerUpdateDto.id(), 
				StringUtils.nullIfEmpty(customerUpdateDto.name()), 
				StringUtils.nullIfEmpty(customerUpdateDto.phone()), 
				StringUtils.nullIfEmpty(customerUpdateDto.address()));
	}

	public static CustomerResponseDto toResponseDto(Customer customer) {
		return new CustomerResponseDto(
				customer.getId(), 
				customer.getName(), 
				customer.getPhone(),
				customer.getAddress());
	}

}
