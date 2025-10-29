package com.tmb.model.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.tmb.model.dao.DatabaseConnection;
import com.tmb.view.screens.ReportView;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class ReportService {
	
	private final DatabaseConnection db;

	public ReportService(DatabaseConnection db) {
		this.db = db;
	}

	public void generateCustomerReport() {

		try (Connection conn = db.getConnection();
				InputStream reportStream = getClass().getResourceAsStream("/reports/Customer_report.jasper")) {
			
			Map<String, Object> params = new HashMap<>();
			params.put("LOGO_PATH", getClass().getResource("/images/logos/logo.png").getPath());
			
			JasperPrint print = JasperFillManager.fillReport(reportStream, params, conn);

			JRViewer viewer = new JRViewer(print);
			ReportView reportView = new ReportView(viewer);
			reportView.setVisible(true);
			
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
}
