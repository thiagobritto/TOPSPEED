package com.tmb.model.mappers;

import com.tmb.model.dto.OSRegisterDto;
import com.tmb.model.entities.Customer;
import com.tmb.model.entities.OS;

public class OSMapper {

	private OSMapper() {
		// static
	}
	
	public static OS toEntity(OSRegisterDto osRegisterDto) {
		Customer customer = new Customer(
				osRegisterDto.customerSearchDto().id(), 
				osRegisterDto.customerSearchDto().name(), 
				osRegisterDto.customerSearchDto().phone(), 
				osRegisterDto.customerSearchDto().address());
		
		return new OS(0, 
				customer, 
				osRegisterDto.description(), 
				osRegisterDto.value(), 
				null, // nulo para registro
				osRegisterDto.status());
	}
	
	
}
