package com.tmb.model.mappers;

import com.tmb.dto.CustomerRegisterDto;
import com.tmb.dto.CustomerResponseDto;
import com.tmb.dto.CustomerUpdateDto;
import com.tmb.model.entities.Customer;
import com.tmb.utils.StringUtils;

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
