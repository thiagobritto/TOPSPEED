package com.tmb.model.mappers;

import com.tmb.model.dto.CustomerResponseDto;
import com.tmb.model.dto.OSRegisterDto;
import com.tmb.model.dto.OSResponseDto;
import com.tmb.model.entities.Customer;
import com.tmb.model.entities.OS;

public class OSMapper {

	private OSMapper() {
		// static
	}
	
	public static OS toEntity(OSRegisterDto osRegisterDto) {
		Customer customer = new Customer(
				osRegisterDto.customerResponseDto().id(), 
				osRegisterDto.customerResponseDto().name(), 
				osRegisterDto.customerResponseDto().phone(), 
				osRegisterDto.customerResponseDto().address());
		
		return new OS(0, 
				customer, 
				osRegisterDto.description(), 
				osRegisterDto.value(), 
				null, // nulo para registro
				osRegisterDto.status());
	}
	
	public static OSResponseDto toResponseDto(OS os) {
		CustomerResponseDto customerResponseDto = new CustomerResponseDto(
				os.getCustomer().getId(), 
				os.getCustomer().getName(), 
				os.getCustomer().getPhone(), 
				os.getCustomer().getAddress());
		
		return new OSResponseDto(
				os.getId(), 
				customerResponseDto, 
				os.getDescription(), 
				os.getValue(), 
				os.getCreatedAt(), 
				os.getStatus());
	}
	
}
