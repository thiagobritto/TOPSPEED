package com.tmb.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tmb.model.dto.CustomerDataSearchDto;
import com.tmb.model.services.CustomerService;

public class CustomerController {

	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	private final CustomerService customerService;	

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	public List<CustomerDataSearchDto> getByName(String text) {
		return customerService.getByName(text);
	}
		
}
