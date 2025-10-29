package com.tmb.model.services;

import com.tmb.model.dao.DatabaseConnection;
import com.tmb.model.dao.DatabaseFactory;

public class ServiceFactory {

	public static ReportService createReportService() {
		DatabaseConnection database = DatabaseFactory.createDatabase();
		return new ReportService(database);
	}
	
}
