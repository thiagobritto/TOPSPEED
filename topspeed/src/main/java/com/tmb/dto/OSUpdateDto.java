package com.tmb.dto;

import java.math.BigDecimal;

import com.tmb.model.entities.OSStatus;

public record OSUpdateDto(
		long id, 
		CustomerResponseDto customerResponseDto, 
		String item, 
		String description, 
		String service, 
		BigDecimal value,
		OSStatus status) {

}
