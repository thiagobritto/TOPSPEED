package com.tmb.model.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static Connection instance;

	private DatabaseConnection() {
		try {
			// Caminho absoluto do banco
			String dbPath = new File("databases/TOPSPEED.FDB").getAbsolutePath();

			// Define onde estão as DLLs do Firebird Embedded
			System.setProperty("java.library.path", new File("fbembed").getAbsolutePath());

			// Carrega o driver do Jaybird 6
			Class.forName("org.firebirdsql.jdbc.FBDriver");

			// URL no modo EMBEDDED
			String url = "jdbc:firebirdsql:embedded:" + dbPath + "?wireCrypt=DISABLED";

			// Conecta com o usuário padrão
			instance = DriverManager.getConnection(url, "SYSDBA", "masterkey");

			System.out.println("✅ Conectado ao Firebird Embedded com sucesso!");

		} catch (ClassNotFoundException e) {
			System.err.println("❌ Driver Jaybird não encontrado: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("❌ Erro ao conectar: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance() {
		if (instance == null) {
			new DatabaseConnection();
		}
		return instance;
	}
}
