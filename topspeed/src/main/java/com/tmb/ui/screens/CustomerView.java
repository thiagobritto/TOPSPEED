package com.tmb.ui.screens;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.tmb.ui.components.AppTextField;

import net.miginfocom.swing.MigLayout;

public class CustomerView extends AbstractFormRegister {

	private static final long serialVersionUID = 1L;
	
	public CustomerView() {
		initComponents();
	}

	private void initComponents() {
		setTitle("Cadastro de clientes");
		
		JPanel formPanel = new JPanel(new MigLayout("wrap 6", "[right][left][right][left]", "[][]"));
		contentPane.add(formPanel, BorderLayout.CENTER);

		JLabel lblCode = new JLabel("ID:");
		formPanel.add(lblCode);
		
		AppTextField txtCode = new AppTextField(5);
		txtCode.setEditable(false);
		formPanel.add(txtCode);
		
		JLabel lblName = new JLabel("Nome:");
		formPanel.add(lblName);
		
		AppTextField txtName = new AppTextField(20);
		formPanel.add(txtName);
		
		JLabel lblFone = new JLabel("Telefone:");
		formPanel.add(lblFone);
		
		AppTextField txtFone = new AppTextField(12);
		formPanel.add(txtFone);
		
		
		JLabel lblNumber = new JLabel("CPF:");
		formPanel.add(lblNumber);
		
		AppTextField txtNumber = new AppTextField(12);
		formPanel.add(txtNumber, "span 3");
		
	}

}
