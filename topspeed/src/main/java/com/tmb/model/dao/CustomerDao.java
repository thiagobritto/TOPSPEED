package com.tmb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmb.model.entities.Customer;

public class CustomerDao {

	private final DatabaseConnection db;

	public CustomerDao(DatabaseConnection db) {
		this.db = db;
	}

	public void insert(Customer customer) throws SQLException {
		String sql = "INSERT_UNLOCKED INTO TB_CUSTOMER (NAME, PHONE, ADDRESS) VALUES (?, ?, ?)";

		try (Connection conn = db.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getPhone());
			stmt.setString(3, customer.getAddress());
			stmt.executeUpdate();

			// Recupera o ID gerado automaticamente
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					long id = rs.getLong(1);
					customer.setId(id);
				}
			}
		}
	}

	public List<Customer> findAll() throws SQLException {
		List<Customer> customers = new ArrayList<>();
		String sql = "SELECT * FROM TB_CUSTOMER";

		try (Connection conn = db.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Customer cliente = new Customer(rs.getLong("ID"), rs.getString("NAME"), rs.getString("PHONE"),
						rs.getString("ADDRESS"));
				customers.add(cliente);
			}

		}

		return customers;
	}

	public void update(Customer customer) throws SQLException {
		String sql = "UPDATE TB_CUSTOMER SET NAME = ?, PHONE = ?, ADDRESS = ? WHERE ID = ?";

		try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getPhone());
			stmt.setString(3, customer.getAddress());
			stmt.setLong(4, customer.getId());
			stmt.executeUpdate();

		}
	}

	public void delete(Long id) throws SQLException {
		String sql = "DELETE FROM TB_CUSTOMER WHERE ID = ?";

		try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, id);
			stmt.executeUpdate();

		}
	}
	
	public List<Customer> findByName(String name) throws SQLException {
		List<Customer> customers = new ArrayList<>();
		String sql = "SELECT * FROM TB_CUSTOMER WHERE NAME LIKE ?";

		try (Connection conn = db.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, name + "%");

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Customer customer = new Customer(rs.getLong("ID"), rs.getString("NAME"), rs.getString("PHONE"),
							rs.getString("ADDRESS"));
					customers.add(customer);
				}
			}

		}

		return customers;
	}

}
