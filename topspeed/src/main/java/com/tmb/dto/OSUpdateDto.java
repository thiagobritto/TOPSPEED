package com.tmb.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.tmb.model.entities.OSStatus;

public record OSUpdateDto(long id, CustomerResponseDto customerResponseDto, String description, BigDecimal value,
		OSStatus status) {

}
