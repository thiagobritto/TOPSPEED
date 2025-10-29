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
import com.tmb.dto.OSUpdateDto;
import com.tmb.model.dao.CustomerDao;
import com.tmb.model.dao.OSDao;
import com.tmb.model.entities.OS;
import com.tmb.model.mappers.CustomerMapper;
import com.tmb.model.mappers.OSMapper;
import com.tmb.model.validators.OSValidator;

public class OSService {

	private static final Logger logger = LogManager.getLogger(OSService.class);
	private final OSDao osDao;
	private final CustomerDao customerDao;
	private final OSValidator osValidator;

	public OSService(OSDao osDao, CustomerDao customerDao, OSValidator osValidator) {
		this.osDao = osDao;
		this.customerDao = customerDao;
		this.osValidator = osValidator;
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
	
	public void update(OSUpdateDto osUpdateDto) {
		try {
			osValidator.validate(osUpdateDto);

			var os = OSMapper.toEntity(osUpdateDto);
			osDao.update(os);
		} catch (IllegalArgumentException e) {
			logger.warn("Falha de validação ao atualizar OS: {}", e.getMessage());
			throw e;
		} catch (SQLException e) {
			logger.error("Erro ao atualizar OS: {}", e.getMessage(), e);
			throw new RuntimeException("Ocorreu um erro inesperado ao atualizar a OS. Tente novamente.");
		}
	}
	
	public List<CustomerResponseDto> getCustomersByName(String name) {
		try {
			return customerDao.findByName(name).stream().map(CustomerMapper::toResponseDto).toList();
		} catch (SQLException e) {
			logger.error("Houve um erro ao buscar clientes palo nome: " + name, e);
			return Collections.emptyList();
		}
	}
	
	public List<OSResponseDto> getAllOSByCustomerName(String name) {
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

	public void delete(long id) {
		try {
			osValidator.validate(id);
			osDao.delete(id);	
		} catch (IllegalArgumentException e) {
			logger.warn("Falha de validação ao excluir OS: {}", e.getMessage());
			throw e;
		} catch (SQLException e) {
			logger.error("Erro ao excluir OS: {}", e.getMessage(), e);
			throw new RuntimeException("Ocorreu um erro inesperado ao tentar remover o cliente. Tente novamente.");
		}
	}	
	
}
