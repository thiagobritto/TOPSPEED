package com.tmb.view.screens;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.tmb.controller.CustomerController;
import com.tmb.model.dto.CustomerDataSearchDto;

public class CustomerView extends AbstractFormRegister {

	private static final long serialVersionUID = 1L;
	private CustomerController controller;

	private JTextField txtCode;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtAddress;

	private List<CustomerDataSearchDto> customerList = new ArrayList<>();;

	public CustomerView(CustomerController customerController) {
		controller = customerController;
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

		setStatusScreen(StatusScreen.BEFORE_UPDATE);
	}

	@Override
	public void onEdit() {
		setStatusScreen(StatusScreen.UPDATE);
	}

	@Override
	public void onSearch() {
		SearchView searchView = ScreenFactory.createSearchView();
		searchView.setTableHeaders("ID", "NOME", "TELEFONE");
		searchView.onSearch(text -> {
			customerList = controller.getByName(text);
			searchView.updateTable(customerList, d -> new Object[] { d.id(), d.name(), d.phone() });
		});
		searchView.setVisible(true);

		if (searchView.isSelectedRow()) {
			CustomerDataSearchDto customerDataSearchDto = customerList.get(searchView.getSelectedIndex());
			txtCode.setText(Long.toString(customerDataSearchDto.id()));
			txtName.setText(customerDataSearchDto.name());
			txtPhone.setText(customerDataSearchDto.phone());
			txtAddress.setText(customerDataSearchDto.address());
			setStatusScreen(StatusScreen.BEFORE_UPDATE);
		}

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

		if (status.equals(StatusScreen.BEFORE_UPDATE)) {
			setEnabledFields(false);
		} else if (status.equals(StatusScreen.UPDATE)) {
			setEnabledFields(true);
		} else if (status.equals(StatusScreen.BEFORE_INSERT)) {
			setEnabledFields(false);
		} else if (status.equals(StatusScreen.INSERT)) {
			setEnabledFields(true);
			cleanFields();
		}

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
