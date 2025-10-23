package com.tmb.model.dao;

import java.sql.Connection;

import com.tmb.model.entities.Customer;

public class CustomerDAO {

	private final Connection conn;

	public CustomerDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void save(Customer customer) {
		
	}
		
}
