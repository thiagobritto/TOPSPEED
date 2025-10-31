
package com.tmb.controller;

import javax.swing.JOptionPane;

import com.tmb.model.services.ReportService;
import com.tmb.view.screens.MainView;
import com.tmb.view.screens.ReportView;

import net.sf.jasperreports.view.JasperViewer;

public class MainController {

	private final MainView view;
	private final ReportService report;

	public MainController(MainView view, ReportService reportService) {
		this.view = view;
		this.report = reportService;
	}

	public void generateCustomerReport() {
		try {
			
			JasperViewer viewer = report.load("customer_report.jasper");
			ReportView reportView = new ReportView(viewer);
			
			reportView.setTitle("Relatório de clientes");
			reportView.setVisible(true);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Erro inesperado: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
