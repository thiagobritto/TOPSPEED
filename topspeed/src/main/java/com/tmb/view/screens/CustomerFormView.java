package com.tmb.view.screens;

import java.awt.BorderLayout;
import java.util.function.Function;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.tmb.controller.CustomerFormController;
import com.tmb.dto.CustomerRegisterDto;
import com.tmb.dto.CustomerResponseDto;
import com.tmb.dto.CustomerUpdateDto;

public class CustomerFormView extends AbstractFormView {

	private static final long serialVersionUID = 1L;
	private final CustomerFormController controller;

	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtAddress;

	public CustomerFormView(Function<CustomerFormView, CustomerFormController> controller) {
		this.controller = controller.apply(this);
		this.initComponents();
	}

	private void initComponents() {
		setTitle("Cadastro de Clientes");

		JPanel form = new JPanel(null);
		formPanel.add(form, BorderLayout.CENTER);

		JLabel lblCode = new JLabel("ID");
		lblCode.setBounds(10, 10, 34, 15);
		form.add(lblCode);

		txtCode = new JTextField();
		txtCode.setBounds(10, 30, 50, 25);
		txtCode.setEditable(false);
		form.add(txtCode);

		JLabel lblName = new JLabel("Nome *");
		lblName.setBounds(70, 10, 112, 15);
		form.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(69, 30, 270, 25);
		form.add(txtName);

		JLabel lblPhone = new JLabel("Telefone");
		lblPhone.setBounds(351, 9, 112, 15);
		form.add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setBounds(351, 30, 164, 25);
		form.add(txtPhone);

		JLabel lblAddress = new JLabel("Endereço");
		lblAddress.setBounds(10, 60, 112, 15);
		form.add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setBounds(10, 80, 505, 25);
		form.add(txtAddress);

		SwingUtilities.invokeLater(() -> setFormStatus(FormStatus.INSERT_BLOCKED));
	}

	@Override
	public void onNew() {
		cleanFields();
		setFormStatus(FormStatus.INSERT_UNLOCKED);

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
		} else {
			CustomerUpdateDto customerUpdateDto = new CustomerUpdateDto(Long.parseLong(id), name, phone, address);
			controller.updateCustomer(customerUpdateDto);
		}

		requestFocus();
	}

	@Override
	public void onEdit() {
		setFormStatus(FormStatus.UPDATE_UNLOCKED);

		txtName.requestFocusInWindow();
		txtName.select(0, 0);
	}

	@Override
	public void onDelete() {
		int option = JOptionPane.showConfirmDialog(this, "Deseja remover o cliente: " + txtName.getText(),
				"Confirmaçãp", JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			controller.deleteCustomer(Long.parseLong(txtCode.getText()));
			requestFocus();
		}		
	}
	
	@Override
	public void onSearch() {
		controller.searchCustomer();
		requestFocus();
	}

	@Override
	public void onCancel() {
		FormStatus formStatus = getFormStatus();
		if (formStatus.equals(FormStatus.INSERT_UNLOCKED) || formStatus.equals(FormStatus.UPDATE_BLOCKED)) {
			setFormStatus(FormStatus.INSERT_BLOCKED);
		}
	}

	@Override
	public void setFormStatus(FormStatus status) {
		super.setFormStatus(status);
		setEnabledFields(status.equals(FormStatus.UPDATE_UNLOCKED) || status.equals(FormStatus.INSERT_UNLOCKED));
	}

	public void cleanFields() {
		txtCode.setText("");
		txtName.setText("");
		txtPhone.setText("");
		txtAddress.setText("");
	}

	public void fillFields(CustomerResponseDto customerResponseDto) {
		txtCode.setText(Long.toString(customerResponseDto.id()));
		txtName.setText(customerResponseDto.name());
		txtPhone.setText(customerResponseDto.phone());
		txtAddress.setText(customerResponseDto.address());
	}

	private void setEnabledFields(boolean enabled) {
		txtCode.setEnabled(enabled);
		txtName.setEnabled(enabled);
		txtPhone.setEnabled(enabled);
		txtAddress.setEnabled(enabled);
	}

}
