package com.tmb.model.services;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tmb.model.dao.CustomerDao;
import com.tmb.model.dao.OSDao;
import com.tmb.model.dto.CustomerSearchDto;
import com.tmb.model.mappers.CustomerMapper;

public class OSService {

	private static final Logger logger = LogManager.getLogger(OSService.class);
	private final CustomerDao customerDao;
	private final OSDao osDao;

	public OSService(CustomerDao customerDao, OSDao osDao) {
		this.customerDao = customerDao;
		this.osDao = osDao;
	}
	
	public void save() {
		try {
			osDao.insert(null);

		} catch (IllegalArgumentException e) {
			logger.warn("Falha de validação ao salvar cliente: {}", e.getMessage());
			throw e;
		} catch (SQLException e) {	
			logger.error("Erro ao salvar cliente: {}", e.getMessage(), e);
			throw new RuntimeException("Ocorreu um erro inesperado ao salvar o cliente. Tente novamente.");
		}
	}
	
	public List<CustomerSearchDto> getByName(String name) {
		try {
			return customerDao.findByName(name).stream().map(CustomerMapper::toSearchDto).toList();
		} catch (SQLException e) {
			logger.error("Houve um erro ao buscar clientes palo nome: " + name, e);
			return Collections.emptyList();
		}
	}	
	
}
