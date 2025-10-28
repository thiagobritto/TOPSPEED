package com.tmb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tmb.model.entities.Customer;
import com.tmb.model.entities.OS;
import com.tmb.model.entities.OSStatus;
import com.tmb.utils.DateTimeUtils;

public class OSDao {

	private final DatabaseConnection db;

	public OSDao(DatabaseConnection db) {
		this.db = db;
	}

	// insert
	public void insert(OS os) throws SQLException {
		String sql = "INSERT INTO TB_OS (ID_CUSTOMER, DESCRIPTION, VALUE, STATUS) VALUES (?, ?, ?, ?)";

		try (Connection conn = db.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setLong(1, os.getCustomer().getId());
			stmt.setString(2, os.getDescription());
			stmt.setBigDecimal(3, os.getValue());
			stmt.setInt(4, os.getStatus().getId());
			stmt.executeUpdate();

			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (!rs.next()) {
					throw new SQLException("Erro ao recuperar o ID gerado automaticamente.");
				}
				
				long id = rs.getLong(1);
				String query = "SELECT CREATED_AT FROM TB_OS WHERE ID = " + id;
				
				try (Statement stmt2 = conn.createStatement();
						ResultSet rs2 = stmt2.executeQuery(query)) {
					
					if (!rs2.next()) {
		            	throw new SQLException("Erro ao recuperar o campo CREATED_AT da OS.");
		            }
					
		            os.setId(id);
		            os.setCreatedAt(DateTimeUtils.parseSQLiteDate(rs2.getString("CREATED_AT")));
				}
			}
		}
	}

	// findAll
	// update
	public void update(OS os) throws SQLException {
		String sql = "UPDATE TB_OS SET ID_CUSTOMER = ?, DESCRIPTION = ?, VALUE = ?, STATUS = ? WHERE ID = ?";

		try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, os.getCustomer().getId());
			stmt.setString(2, os.getDescription());
			stmt.setBigDecimal(3, os.getValue());
			stmt.setInt(4, os.getStatus().getId());
			stmt.setLong(5, os.getId());
			stmt.executeUpdate();

		}
	}

	// delete
	// others
	public OS findById(long id) throws SQLException {
		String sql = """
		        SELECT 
		            TB_OS.DESCRIPTION,
		            TB_OS.VALUE,
		            TB_OS.CREATED_AT,
		            TB_OS.STATUS,
		            TB_CUSTOMER.ID AS CUSTOMER_ID,
		            TB_CUSTOMER.NAME,
		            TB_CUSTOMER.PHONE,
		            TB_CUSTOMER.ADDRESS
		        FROM TB_OS
		        LEFT JOIN TB_CUSTOMER ON TB_OS.ID_CUSTOMER = TB_CUSTOMER.ID
		        WHERE TB_OS.ID = ?
		    """;
		
		try (Connection conn = db.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, id);
			
			try (ResultSet rs = stmt.executeQuery()) {
				
				if (!rs.next()) {
					return null;
				}
				
				Customer customer = new Customer(
						rs.getLong("CUSTOMER_ID"), 
						rs.getString("NAME"), 
						rs.getString("PHONE"), 
						rs.getString("ADDRESS"));
				
				OS os = new OS(
						id, 
						customer, 
						rs.getString("DESCRIPTION"), 
						rs.getBigDecimal("VALUE"), 
						DateTimeUtils.parseSQLiteDate(rs.getString("CREATED_AT")), 
						OSStatus.valueOf(rs.getInt("STATUS")));
				
				return os;
			}

		}
	}
	
	public List<OS> findByCustomerName(String name) throws SQLException {
		List<OS> oss = new ArrayList<>();
		String sql = """
		        SELECT 
		            TB_OS.ID AS OS_ID,
		            TB_OS.DESCRIPTION,
		            TB_OS.VALUE,
		            TB_OS.CREATED_AT,
		            TB_OS.STATUS,
		            TB_CUSTOMER.ID AS CUSTOMER_ID,
		            TB_CUSTOMER.NAME,
		            TB_CUSTOMER.PHONE,
		            TB_CUSTOMER.ADDRESS
		        FROM TB_OS
		        LEFT JOIN TB_CUSTOMER ON TB_OS.ID_CUSTOMER = TB_CUSTOMER.ID
		        WHERE TB_CUSTOMER.NAME LIKE ?
		    """;
		
		try (Connection conn = db.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, name + "%");

			try (ResultSet rs = stmt.executeQuery()) {
				
				while (rs.next()) {
					Customer customer = new Customer(
							rs.getLong("CUSTOMER_ID"), 
							rs.getString("NAME"), 
							rs.getString("PHONE"),
							rs.getString("ADDRESS"));
					
					OS os = new OS(
							rs.getLong("OS_ID"), 
							customer, 
							rs.getString("DESCRIPTION"), 
							rs.getBigDecimal("VALUE"), 
							DateTimeUtils.parseSQLiteDate(rs.getString("CREATED_AT")), 
							OSStatus.valueOf(rs.getInt("STATUS")));
					
					oss.add(os);	
				}
			}
			
		}

		return oss;
	}
}
