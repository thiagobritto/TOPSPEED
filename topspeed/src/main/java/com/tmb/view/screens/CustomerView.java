package com.tmb.view.screens;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerView extends AbstractFormRegister {

	private static final long serialVersionUID = 1L;
	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtAddress;
	
	public CustomerView() {
		initComponents();
	}

	private void initComponents() {
		setTitle("Cadastro de clientes");
		
		JPanel formPanel = new JPanel(null);
		getContentPane().add(formPanel, BorderLayout.CENTER);
		
		
		JLabel lblCode = new JLabel("ID:");
		lblCode.setBounds(10, 10, 34, 15);
		formPanel.add(lblCode);
		
		txtCode = new JTextField();
		txtCode.setBounds(10, 30, 50, 25);
		txtCode.setEditable(false);
		txtCode.setEnabled(false);
		formPanel.add(txtCode);
		
		JLabel lblName = new JLabel("Nome:");
		lblName.setBounds(70, 10, 112, 15);
		formPanel.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(69, 30, 270, 25);
		txtName.setEnabled(false);
		formPanel.add(txtName);
		
		JLabel lblPhone = new JLabel("Telefone:");
		lblPhone.setBounds(351, 9, 112, 15);
		formPanel.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(351, 30, 164, 25);
		txtPhone.setEnabled(false);
		formPanel.add(txtPhone);
		
		JLabel lblAddress = new JLabel("Endereço:");
		lblAddress.setBounds(10, 60, 112, 15);
		formPanel.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(10, 80, 505, 25);
		txtAddress.setEnabled(false);
		formPanel.add(txtAddress);
		
	}

	@Override
	public void onNew() {
		releaseFields(true);
		txtName.requestFocus();
		setStatusScreen(StatusScreen.INSERT);
	}

	@Override
	public void onSave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEdit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSearch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCancel() {
		releaseFields(false);
		if (txtCode.getText().isBlank()) {
			setStatusScreen(StatusScreen.PREVIEW);
		} else {
			setStatusScreen(StatusScreen.UPDATE);			
		}
	}
	
	private void releaseFields(boolean b) {
		txtCode.setEnabled(b);
		txtName.setEnabled(b);
		txtPhone.setEnabled(b);
		txtAddress.setEnabled(b);
	}
}
