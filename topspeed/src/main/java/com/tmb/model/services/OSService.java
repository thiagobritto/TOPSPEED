package com.tmb.model.services;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tmb.model.dao.CustomerDao;
import com.tmb.model.dto.CustomerDataSearchDto;
import com.tmb.model.mappers.CustomerMapper;

public class OSService {

	private static final Logger logger = LogManager.getLogger(OSService.class);
	private final CustomerDao customerDao;

	public OSService(CustomerDao customerDao) {
		this.customerDao = customerDao;
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
