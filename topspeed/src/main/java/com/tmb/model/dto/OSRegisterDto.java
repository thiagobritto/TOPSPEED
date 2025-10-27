package com.tmb.model.dto;

import java.math.BigDecimal;

import com.tmb.model.entities.OSStatus;

public record OSRegisterDto(CustomerSearchDto customerSearchDto, String description, BigDecimal value,
		OSStatus status) {

}
