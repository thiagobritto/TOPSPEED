package com.tmb.model.services;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tmb.model.dao.CustomerDao;
import com.tmb.model.dto.CustomerDataSearchDto;
import com.tmb.model.dto.CustomerRegisterDto;
import com.tmb.model.dto.CustomerUpdateDto;
import com.tmb.model.mapper.CustomerMapper;
import com.tmb.model.utils.BusinessException;
import com.tmb.model.utils.CustomerValidator;

public class CustomerService {

	private static final Logger logger = LogManager.getLogger(CustomerService.class);
	private final CustomerDao customerDao;
	private final CustomerValidator customerValidator;

	public CustomerService(CustomerDao customerDao, CustomerValidator customerValidator) {
		this.customerDao = customerDao;
		this.customerValidator = customerValidator;
	}

	public void save(CustomerRegisterDto customerRegisterDto) {
		try {
			customerValidator.validate(customerRegisterDto);

			var customer = CustomerMapper.toEntity(customerRegisterDto);
			customerDao.insert(customer);
		} catch (IllegalArgumentException e) {
			logger.warn("Falha de validação ao salvar cliente: {}", e.getMessage());
			throw e;
		} catch (SQLException e) {	
			if (e.getMessage() != null && e.getMessage().contains("UNIQUE constraint failed")) {
				logger.warn("Telefone duplicado detectado: {}", customerRegisterDto.phone());
				throw new BusinessException(
						"Já existe um cliente cadastrado com o telefone " + customerRegisterDto.phone() + ".");
			}

			logger.error("Erro ao salvar cliente: {}", e.getMessage(), e);
			throw new RuntimeException("Ocorreu um erro inesperado ao salvar o cliente. Tente novamente.");
		}
	}

	public void update(CustomerUpdateDto customerUpdateDto) {
		try {
			customerValidator.validate(customerUpdateDto);

			var customer = CustomerMapper.toEntity(customerUpdateDto);
			customerDao.update(customer);
		} catch (IllegalArgumentException e) {
			logger.warn("Falha de validação ao atualizar cliente: {}", e.getMessage());
			throw e;
		} catch (SQLException e) {
			if (e.getMessage() != null && e.getMessage().contains("UNIQUE constraint failed")) {
				logger.warn("Telefone duplicado detectado: {}", customerUpdateDto.phone());
				throw new BusinessException(
						"Já existe um cliente cadastrado com o telefone " + customerUpdateDto.phone() + ".");
			}

			logger.error("Erro ao atualizar cliente: {}", e.getMessage(), e);
			throw new RuntimeException("Ocorreu um erro inesperado ao atualizar o cliente. Tente novamente.");
		}
	}

	public List<CustomerDataSearchDto> getByName(String name) {
		try {
			return customerDao.findByName(name).stream().map(CustomerMapper::toDataSearchDto).toList();
		} catch (SQLException e) {
			logger.error("Houve um erro ao buscar clientes palo nome: " + name, e);
			return Collections.emptyList();
		}
	}

}
