package com.tmb.dto;

import java.math.BigDecimal;

import com.tmb.model.entities.OSStatus;

public record OSRegisterDto(CustomerResponseDto customerResponseDto, String description, BigDecimal value,
		OSStatus status) {

}
