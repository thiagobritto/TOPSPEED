package com.tmb.dto;

import java.math.BigDecimal;

import com.tmb.model.entities.OSStatus;

public record OSUpdateDto(long id, CustomerResponseDto customerResponseDto, String description, BigDecimal value,
		OSStatus status) {

}
