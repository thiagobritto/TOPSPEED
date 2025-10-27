package com.tmb.model.utils;

import java.math.BigDecimal;

import com.tmb.model.dto.OSRegisterDto;

public class OSValidator {

	public void validate(OSRegisterDto osRegisterDto) {
		if (osRegisterDto == null) {
			throw new IllegalArgumentException("Os dados da OS não podem ser nulos.");
		}
		
		if (osRegisterDto.customerResponseDto() == null) {
			throw new IllegalArgumentException("Os dados do Cliente não podem ser nulos.");
		}

		long idCustomer = osRegisterDto.customerResponseDto().id();
		if (idCustomer < 1) {
			throw new IllegalArgumentException("O cliente não foi selecionado.");
		}

		BigDecimal value = osRegisterDto.value();
		if (value == null || value.compareTo(BigDecimal.ZERO) < 1) {
			throw new IllegalArgumentException("O valor da OS não pode ser R$ : " + value);
		}
	}

}
