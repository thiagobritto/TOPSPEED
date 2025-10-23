package com.tmb.model.services;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.tmb.model.dao.CustomerDao;
import com.tmb.model.dto.CustomerDataSearchDto;

public class CustomerService {

	private final CustomerDao customerDao;

	public CustomerService(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public List<CustomerDataSearchDto> getByName(String name) {
		try {
			return customerDao.findByName(name).stream()
					.map(c -> new CustomerDataSearchDto(c.getId(), c.getName(), c.getPhone(), c.getAddress())).toList();
		} catch (SQLException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
