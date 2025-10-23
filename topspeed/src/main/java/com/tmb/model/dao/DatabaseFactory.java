package com.tmb.model.dao;

public class DatabaseFactory {

	public static DatabaseConnection createDatabase() {
		return SQLiteConnection.getInstance();
	}
	
}
