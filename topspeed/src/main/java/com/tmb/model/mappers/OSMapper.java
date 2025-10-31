package com.tmb.model.mappers;

import com.tmb.dto.CustomerResponseDto;
import com.tmb.dto.OSRegisterDto;
import com.tmb.dto.OSResponseDto;
import com.tmb.dto.OSUpdateDto;
import com.tmb.model.entities.Customer;
import com.tmb.model.entities.OS;
import com.tmb.utils.StringUtils;

public class OSMapper {

	private OSMapper() {
		// static
	}
	
	/**
	 * 
	 * @param osRegisterDto
	 * @return
	 */
	
	public static OS toEntity(OSRegisterDto osRegisterDto) {
		if (osRegisterDto == null) {
			return null;
		}
		
		Customer customer = null;
		
		CustomerResponseDto customerResponseDto = osRegisterDto.customerResponseDto();
		if (customerResponseDto != null) {
			customer = new Customer(
					customerResponseDto.id(), 
					customerResponseDto.name(), 
					customerResponseDto.phone(), 
					customerResponseDto.address());
		}
		
		return new OS(
				0, 
				customer, 
				StringUtils.nullIfEmpty(osRegisterDto.item()), 
				StringUtils.nullIfEmpty(osRegisterDto.description()), 
				StringUtils.nullIfEmpty(osRegisterDto.service()), 
				osRegisterDto.value(), 
				null, // nulo para registro
				osRegisterDto.status());
	}
	
	/**
	 * 
	 * @param osUpdateDto
	 * @return
	 */
	
	public static OS toEntity(OSUpdateDto osUpdateDto) {
		if (osUpdateDto == null) {
			return null;
		}
		
		Customer customer = null;
		
		CustomerResponseDto customerResponseDto = osUpdateDto.customerResponseDto();
		if (customerResponseDto != null) {
			customer = new Customer(
					customerResponseDto.id(), 
					customerResponseDto.name(), 
					customerResponseDto.phone(), 
					customerResponseDto.address());
		}
		
		return new OS(
				osUpdateDto.id(), 
				customer, 
				StringUtils.nullIfEmpty(osUpdateDto.item()), 
				StringUtils.nullIfEmpty(osUpdateDto.description()), 
				StringUtils.nullIfEmpty(osUpdateDto.service()), 
				osUpdateDto.value(), 
				null, // nulo para atualização
				osUpdateDto.status());
	}
	
	/**
	 * 
	 * @param os
	 * @return
	 */
	
	public static OSResponseDto toResponseDto(OS os) {
		if (os == null) {
			return null;
		}
		
		CustomerResponseDto customerResponseDto = null;
		
		Customer customer = os.getCustomer();
		if (customer != null) {
			customerResponseDto = new CustomerResponseDto(
					customer.getId(), 
					customer.getName(), 
					customer.getPhone(), 
					customer.getAddress());
		}
		
		return new OSResponseDto(
				os.getId(), 
				customerResponseDto, 
				os.getItem(), 
				os.getDescription(), 
				os.getService(), 
				os.getValue(), 
				os.getCreatedAt(), 
				os.getStatus());
	}
		
}
