package com.tmb.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.tmb.model.entities.OSStatus;

public record OSResponseDto(
		long id, 
		CustomerResponseDto customerResponseDto, 
		String item, 
		String description, 
		String service, 
		BigDecimal value,
		LocalDateTime createdAt, OSStatus status) {

}
