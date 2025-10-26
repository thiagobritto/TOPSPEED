package com.tmb.view.screens;

import java.awt.BorderLayout;
import java.util.function.Function;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.tmb.controller.CustomerFormController;
import com.tmb.model.dto.CustomerDataSearchDto;
import com.tmb.model.dto.CustomerRegisterDto;
import com.tmb.model.dto.CustomerUpdateDto;

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
	public void onSearch() {
		controller.searchCustomer();
		requestFocus();
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

	public void fillFields(CustomerDataSearchDto customerDataSearchDto) {
		txtCode.setText(Long.toString(customerDataSearchDto.id()));
		txtName.setText(customerDataSearchDto.name());
		txtPhone.setText(customerDataSearchDto.phone());
		txtAddress.setText(customerDataSearchDto.address());
	}

	private void setEnabledFields(boolean enabled) {
		txtCode.setEnabled(enabled);
		txtName.setEnabled(enabled);
		txtPhone.setEnabled(enabled);
		txtAddress.setEnabled(enabled);
	}

}
