package com.tmb.model.mapper;

import com.tmb.model.dto.CustomerDataSearchDto;
import com.tmb.model.dto.CustomerRegisterDto;
import com.tmb.model.dto.CustomerUpdateDto;
import com.tmb.model.entities.Customer;

public class CustomerMapper {

	public static Customer toEntity(CustomerRegisterDto customerRegisterDto) {
		return new Customer(0, customerRegisterDto.name(), customerRegisterDto.phone(), customerRegisterDto.address());
	}
	
	public static Customer toEntity(CustomerUpdateDto customerUpdateDto) {
		return new Customer(customerUpdateDto.id(), customerUpdateDto.name(), customerUpdateDto.phone(), customerUpdateDto.address());
	}
	
	public static CustomerDataSearchDto toDataSearchDto(Customer customer) {
		return new CustomerDataSearchDto(customer.getId(), customer.getName(), customer.getPhone(), customer.getAddress());
	}
	
}
