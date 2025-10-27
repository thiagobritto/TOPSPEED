package com.tmb.model.services;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tmb.model.dao.CustomerDao;
import com.tmb.model.dao.OSDao;
import com.tmb.model.dto.CustomerResponseDto;
import com.tmb.model.dto.OSRegisterDto;
import com.tmb.model.dto.OSResponseDto;
import com.tmb.model.mappers.CustomerMapper;
import com.tmb.model.mappers.OSMapper;
import com.tmb.model.utils.OSValidator;

public class OSService {

	private static final Logger logger = LogManager.getLogger(OSService.class);
	private final OSValidator osValidator;
	private final CustomerDao customerDao;
	private final OSDao osDao;

	public OSService(OSValidator osValidator, CustomerDao customerDao, OSDao osDao) {
		this.osValidator = osValidator;
		this.customerDao = customerDao;
		this.osDao = osDao;
	}
	
	public Optional<OSResponseDto> save(OSRegisterDto osRegisterDto) {
		try {
			osValidator.validate(osRegisterDto);

			var os = OSMapper.toEntity(osRegisterDto);
			osDao.insert(os);
			
			var osResponseDto = OSMapper.toResponseDto(os);
			return Optional.of(osResponseDto);
		} catch (IllegalArgumentException e) {
			logger.warn("Falha de validação ao salvar OS: {}", e.getMessage());
			throw e;
		} catch (SQLException e) {	
			logger.error("Erro ao salvar OS: {}", e.getMessage(), e);
			throw new RuntimeException("Ocorreu um erro inesperado ao salvar OS. Tente novamente.");
		}
	}
	
	public List<CustomerResponseDto> getByName(String name) {
		try {
			return customerDao.findByName(name).stream().map(CustomerMapper::toResponseDto).toList();
		} catch (SQLException e) {
			logger.error("Houve um erro ao buscar clientes palo nome: " + name, e);
			return Collections.emptyList();
		}
	}	
	
}
