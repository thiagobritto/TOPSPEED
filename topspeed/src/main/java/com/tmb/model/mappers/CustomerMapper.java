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

	/**
	 * 
	 * @param customerRegisterDto
	 * @return
	 */
	
	public static Customer toEntity(CustomerRegisterDto customerRegisterDto) {
		if (customerRegisterDto == null) {
			return null;
		}
		
		return new Customer(
				0, 
				StringUtils.nullIfEmpty(customerRegisterDto.name()), 
				StringUtils.nullIfEmpty(customerRegisterDto.phone()), 
				StringUtils.nullIfEmpty(customerRegisterDto.address()));
	}
	
	/**
	 * 
	 * @param customerUpdateDto
	 * @return
	 */

	public static Customer toEntity(CustomerUpdateDto customerUpdateDto) {
		if (customerUpdateDto == null) {
			return null;
		}
		
		return new Customer(
				customerUpdateDto.id(), 
				StringUtils.nullIfEmpty(customerUpdateDto.name()), 
				StringUtils.nullIfEmpty(customerUpdateDto.phone()), 
				StringUtils.nullIfEmpty(customerUpdateDto.address()));
	}
	
	/**
	 * 
	 * @param customer
	 * @return
	 */

	public static CustomerResponseDto toResponseDto(Customer customer) {
		if (customer == null) {
			return null;
		}
		
		return new CustomerResponseDto(
				customer.getId(), 
				customer.getName(), 
				customer.getPhone(),
				customer.getAddress());
	}

}
