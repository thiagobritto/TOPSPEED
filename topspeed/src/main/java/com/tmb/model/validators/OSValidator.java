package com.tmb.model.validators;

import java.math.BigDecimal;

import com.tmb.dto.OSRegisterDto;
import com.tmb.dto.OSUpdateDto;

public class OSValidator {

	public void validate(OSRegisterDto osRegisterDto) {
		if (osRegisterDto == null) {
			throw new IllegalArgumentException("Os dados da OS não podem ser nulos.");
		}

		if (osRegisterDto.customerResponseDto() == null) {
			throw new IllegalArgumentException("Os dados do Cliente não podem ser nulos.");
		}

		if (osRegisterDto.customerResponseDto().id() < 1) {
			throw new IllegalArgumentException("O cliente não foi selecionado.");
		}
		
		String item = osRegisterDto.item();
		if (item == null || item.isBlank()) {
			throw new IllegalArgumentException("Preencha todos os campos obrigatorios (*).");
		}

		BigDecimal value = osRegisterDto.value();
		if (value == null || value.compareTo(BigDecimal.ZERO) < 1) {
			throw new IllegalArgumentException("O valor da OS não pode ser R$ : " + value);
		}
	}

	public void validate(OSUpdateDto osUpdateDto) {
		if (osUpdateDto == null) {
			throw new IllegalArgumentException("Os dados da OS não podem ser nulos.");
		}

		if (osUpdateDto.id() < 1) {
			throw new IllegalArgumentException("O número da OS não é valido.");
		}

		if (osUpdateDto.customerResponseDto() == null) {
			throw new IllegalArgumentException("Os dados do Cliente não podem ser nulos.");
		}

		if (osUpdateDto.customerResponseDto().id() < 1) {
			throw new IllegalArgumentException("O cliente não foi selecionado.");
		}
		
		String item = osUpdateDto.item();
		if (item == null || item.isBlank()) {
			throw new IllegalArgumentException("Preencha todos os campos obrigatorios (*).");
		}

		BigDecimal value = osUpdateDto.value();
		if (value == null || value.compareTo(BigDecimal.ZERO) < 1) {
			throw new IllegalArgumentException("O valor da OS não pode ser R$ : " + value);
		}
	}
	
	public void validate(long id) {
		if (id < 1) {
			throw new IllegalArgumentException("O id da OS é invalido.");
		}
	}

}
