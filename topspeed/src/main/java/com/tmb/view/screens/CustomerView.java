package com.tmb.view.screens;

import java.awt.BorderLayout;
import java.util.function.Function;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.tmb.controller.CustomerController;
import com.tmb.model.dto.CustomerDataSearchDto;
import com.tmb.model.dto.CustomerRegisterDto;
import com.tmb.model.dto.CustomerUpdateDto;

public class CustomerView extends AbstractFormRegister {

	private static final long serialVersionUID = 1L;
	private CustomerController controller;

	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtAddress;

	public CustomerView(Function<CustomerView, CustomerController> customerController) {
		controller = customerController.apply(this);
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
		formPanel.add(txtCode);

		JLabel lblName = new JLabel("Nome: *");
		lblName.setBounds(70, 10, 112, 15);
		formPanel.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(69, 30, 270, 25);
		formPanel.add(txtName);

		JLabel lblPhone = new JLabel("Telefone:");
		lblPhone.setBounds(351, 9, 112, 15);
		formPanel.add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setBounds(351, 30, 164, 25);
		formPanel.add(txtPhone);

		JLabel lblAddress = new JLabel("Endereço:");
		lblAddress.setBounds(10, 60, 112, 15);
		formPanel.add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setBounds(10, 80, 505, 25);
		formPanel.add(txtAddress);

		setStatusScreen(StatusScreen.BEFORE_INSERT);
	}

	@Override
	public void onNew() {
		setStatusScreen(StatusScreen.INSERT);
		txtName.requestFocus();
	}

	@Override
	public void onSave() {
		String id = txtCode.getText();
		String name = txtName.getText();
		String phone = txtPhone.getText();
		String address = txtAddress.getText();
		
		if (id.isBlank()) {
			CustomerRegisterDto customerRegisterDto = new CustomerRegisterDto(name, phone, address);
			controller.saveCustomer(customerRegisterDto);				
			setStatusScreen(StatusScreen.BEFORE_UPDATE);
		} else {
			CustomerUpdateDto customerUpdateDto = new CustomerUpdateDto(Long.parseLong(id), name, phone, address);
			// controller.updateCustomer(customerUpdateDto);
			setStatusScreen(StatusScreen.BEFORE_UPDATE);
		}
		
	}

	@Override
	public void onEdit() {
		setStatusScreen(StatusScreen.UPDATE);

		txtName.requestFocusInWindow();
		txtName.select(0, 0);
	}

	@Override
	public void onSearch() {
		controller.searchCustomer();
		requestFocus();
	}

	@Override
	public void onDelete() {

	}

	@Override
	public void onCancel() {
		StatusScreen statusScreen = getStatusScreen();
		if (statusScreen.equals(StatusScreen.INSERT)) {
			setStatusScreen(StatusScreen.BEFORE_INSERT);
		} else if (statusScreen.equals(StatusScreen.BEFORE_UPDATE)) {
			setStatusScreen(StatusScreen.BEFORE_INSERT);
		}
	}

	@Override
	public void setStatusScreen(StatusScreen status) {
		super.setStatusScreen(status);
		setEnabledFields(status.equals(StatusScreen.UPDATE) || status.equals(StatusScreen.INSERT));

		if (status.equals(StatusScreen.INSERT)) {
			cleanFields();
		}
	}

	public void fillFields(CustomerDataSearchDto customerDataSearchDto) {
		txtCode.setText(Long.toString(customerDataSearchDto.id()));
		txtName.setText(customerDataSearchDto.name());
		txtPhone.setText(customerDataSearchDto.phone());
		txtAddress.setText(customerDataSearchDto.address());
		
		setStatusScreen(StatusScreen.BEFORE_UPDATE);
	}

	private void setEnabledFields(boolean enabled) {
		txtCode.setEnabled(enabled);
		txtName.setEnabled(enabled);
		txtPhone.setEnabled(enabled);
		txtAddress.setEnabled(enabled);
	}

	private void cleanFields() {
		txtCode.setText("");
		txtName.setText("");
		txtPhone.setText("");
		txtAddress.setText("");
	}

}
