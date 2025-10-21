package com.tmb.ui.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tmb.ui.components.AppCard;

public class HomeView extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(HomeView.class);
	
	private JPanel contentPane;
	private JTable table;

	public HomeView() {	
		initComponents();
	}

	private void initComponents() {
		setFrameIcon(null);
		setTitle("Home");
		setSize(788, 600);
		maximizedOnOpen();
		
		contentPane = new JPanel(new BorderLayout(10, 10));
		setContentPane(contentPane);
		
		JPanel header = new JPanel(new GridLayout(1, 4, 10, 0));
		header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.add(header, BorderLayout.NORTH);
		
		AppCard card_1 = new AppCard(new Color(0,139,139));
		card_1.setTextColor(Color.WHITE);
		card_1.setTopText("Vendas");
		card_1.setLeftText("5.000");
		card_1.setRightText("25% comprometido");
		card_1.setBottonText("Relatorio de venda de hoje.");
		header.add(card_1);
		
		AppCard card_2 = new AppCard(new Color(255,140,0));
		card_2.setTextColor(Color.WHITE);
		card_2.setTopText("Vendas");
		header.add(card_2);
		
		AppCard card_3 = new AppCard(new Color(0, 200, 0));
		card_3.setTextColor(Color.WHITE);
		card_3.setTopText("Vendas");
		header.add(card_3);
		
		AppCard card_4 = new AppCard(new Color(139,0,139));
		card_4.setTextColor(Color.WHITE);
		card_4.setTopText("Vendas");
		header.add(card_4);
		
		JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.add(mainPanel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel(new BorderLayout(10, 10));
		mainPanel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Dados de OS");
		panel_1.add(lblNewLabel, BorderLayout.NORTH);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"N\u00FAmero", "Cliente", "Data", "Status"
			}
		));
		panel_1.add(new JScrollPane(table), BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel(new BorderLayout(10, 10));
		mainPanel.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Anotações");
		panel_2.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JTextArea textArea = new JTextArea();
		panel_2.add(new JScrollPane(textArea), BorderLayout.CENTER);
		
	}
	
	private void maximizedOnOpen() {
		try {
			setMaximum(true);
		} catch (Exception e) {
			logger.info("Erro ao maximizar tela de cadastro de clientes após inicialização.");
		}
	}

}
