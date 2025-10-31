package com.tmb.model.services;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tmb.model.dao.DatabaseConnection;
import com.tmb.utils.ReportUtils;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ReportService {

	private static final Logger logger = LogManager.getLogger(ReportService.class);
	private final DatabaseConnection db;

	public ReportService(DatabaseConnection db) {
		this.db = db;
	}

	public JasperViewer load(String reportFileName) {
		try (Connection connection = db.getConnection();
				InputStream reportStream = getClass().getResourceAsStream("/reports/" + reportFileName)) {

			if (reportStream == null) {
				throw new RuntimeException("Relatório não encontrado: " + reportFileName);
			}

			Map<String, Object> params = new HashMap<>();
			params.put("LOGO", ReportUtils.loadLogo());

			JasperPrint print = JasperFillManager.fillReport(reportStream, params, connection);
			return new JasperViewer(print, false);

		} catch (Exception e) {
			logger.error("Erro ao carregar relatório: {}", e.getMessage(), e);
			throw new RuntimeException("Ocorreu um erro ao carregar o relatório. Tente novamente.");
		}
	}

	public JasperViewer load(String reportFileName, Map<String, Object> params) {
		try (Connection connection = db.getConnection();
				InputStream reportStream = getClass().getResourceAsStream("/reports/" + reportFileName)) {

			if (reportStream == null) {
				throw new RuntimeException("Relatório não encontrado: " + reportFileName);
			}

			if (!params.containsKey("LOGO")) {
				params.put("LOGO", ReportUtils.loadLogo());
			}

			JasperPrint print = JasperFillManager.fillReport(reportStream, params, connection);
			return new JasperViewer(print, false);

		} catch (Exception e) {
			logger.error("Erro ao carregar relatório: {}", e.getMessage(), e);
			throw new RuntimeException("Ocorreu um erro ao carregar o relatório. Tente novamente.");
		}
	}

}
