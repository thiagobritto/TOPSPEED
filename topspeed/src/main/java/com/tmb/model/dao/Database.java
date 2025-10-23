package com.tmb.model.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Database {

	private static final Logger logger = LogManager.getLogger(Database.class);
	private static Connection connection;

	private Database() {
		try {
			Class.forName("org.sqlite.JDBC");
			createDatabaseDir();
			connection = DriverManager.getConnection("jdbc:sqlite:databases/TOPSPEED.DB");
		} catch (ClassNotFoundException e) {
			logger.error("Não foi possível carregar o driver JDBC do SQLite: ", e);
		} catch (SQLException e) {
			logger.error("Não foi possível conectar com SQLite: ", e);
		}
	}

	private void createDatabaseDir() {
		File dir = new File("databases");
		if (!dir.exists()) {
			dir.mkdirs(); // cria o diretório se não existir
		}
	}

	public static Connection getConnection() {
		if (connection == null) {
			new Database();
		}
		return connection;
	}
}
