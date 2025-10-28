package com.tmb.model.services;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tmb.dto.CustomerResponseDto;
import com.tmb.dto.OSRegisterDto;
import com.tmb.dto.OSResponseDto;
import com.tmb.model.dao.CustomerDao;
import com.tmb.model.dao.OSDao;
import com.tmb.model.entities.OS;
import com.tmb.model.mappers.CustomerMapper;
import com.tmb.model.mappers.OSMapper;
import com.tmb.model.validators.OSValidator;

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
	
	public List<CustomerResponseDto> getCustomerByName(String name) {
		try {
			return customerDao.findByName(name).stream().map(CustomerMapper::toResponseDto).toList();
		} catch (SQLException e) {
			logger.error("Houve um erro ao buscar clientes palo nome: " + name, e);
			return Collections.emptyList();
		}
	}
	
	public List<OSResponseDto> getOSByCustomerName(String name) {
		try {
			List<OS> osList = osDao.findByCustomerName(name);
			return osList.stream().map(OSMapper::toResponseDto).toList();
		} catch (SQLException e) {
			logger.error("Houve um erro ao buscar OS palo nome do clientes: " + name, e);
			return Collections.emptyList();
		}
	}
	
	public Optional<OSResponseDto> getOSByNumber(long id) {
		try {
			OS os = osDao.findById(id);
			return Optional.ofNullable(OSMapper.toResponseDto(os));
		} catch (SQLException e) {
			logger.error("Houve um erro ao buscar OS palo ID: " + id, e);
			return Optional.empty();
		}
	}	
	
}
