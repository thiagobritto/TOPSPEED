package com.tmb.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.tmb.model.entities.OSStatus;

public record OSResponseDto(long id, CustomerResponseDto customerResponseDto, String description, BigDecimal value,
		LocalDateTime createdAt, OSStatus status) {

}
