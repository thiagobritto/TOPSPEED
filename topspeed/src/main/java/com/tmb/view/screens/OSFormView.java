package com.tmb.view.screens;

import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.tmb.controller.OSFormController;
import com.tmb.model.dto.CustomerResponseDto;
import com.tmb.model.dto.OSRegisterDto;
import com.tmb.model.dto.OSResponseDto;
import com.tmb.model.entities.OSStatus;

public class OSFormView extends AbstractFormView {

	private static final long serialVersionUID = 1L;
	private final OSFormController controller;
	
	private JTextField txtOsId;
	private JTextField txtCustomerName;
	private JTextField txtDate;
	private JComboBox<OSStatus> cbxOSStatus;
	private JTextField txtDescription;
	private JTextField txtValue;
	private JButton btnSearchCustomer;
	
	private CustomerResponseDto customerResponseDto;

	public OSFormView(Function<OSFormView, OSFormController> controller) {
		this.controller = controller.apply(this);
		this.initComponents();
	}

	private void initComponents() {
		setTitle("Cadastro de OS");

		JPanel formPanel = new JPanel(null);
		getContentPane().add(formPanel, BorderLayout.CENTER);
		
		JLabel lblOs = new JLabel("OS");
		lblOs.setBounds(10, 10, 60, 15);
		formPanel.add(lblOs);
		
		txtOsId = new JTextField();
		txtOsId.setEditable(false);
		txtOsId.setBounds(10, 30, 60, 25);
		formPanel.add(txtOsId);
		txtOsId.setColumns(10);
		
		JLabel lblCustomer = new JLabel("Cliente");
		lblCustomer.setBounds(80, 10, 60, 15);
		formPanel.add(lblCustomer);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setEditable(false);
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(80, 30, 200, 25);
		formPanel.add(txtCustomerName);
		
		btnSearchCustomer = new JButton("...");
		btnSearchCustomer.setToolTipText("Localizar cliente");
		btnSearchCustomer.setBounds(290, 30, 25, 25);
		btnSearchCustomer.addActionListener(e -> controller.searchCustomer());
		formPanel.add(btnSearchCustomer);
		
		JLabel lblDate = new JLabel("Data");
		lblDate.setBounds(325, 10, 60, 15);
		formPanel.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setColumns(10);
		txtDate.setBounds(325, 30, 120, 25);
		formPanel.add(txtDate);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(455, 10, 60, 15);
		formPanel.add(lblStatus);
		
		cbxOSStatus = new JComboBox<>(OSStatus.values());
		cbxOSStatus.setBounds(455, 30, 150, 25);
		formPanel.add(cbxOSStatus);
		
		JLabel lblDescription = new JLabel("Descrição");
		lblDescription.setBounds(10, 60, 85, 15);
		formPanel.add(lblDescription);
		
		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(10, 80, 305, 25);
		formPanel.add(txtDescription);
		
		JLabel lblValue = new JLabel("Valor R$");
		lblValue.setBounds(325, 60, 60, 15);
		formPanel.add(lblValue);
		
		txtValue = new JTextField();
		txtValue.setColumns(10);
		txtValue.setBounds(325, 80, 120, 25);
		formPanel.add(txtValue);
		
		SwingUtilities.invokeLater(() -> setFormStatus(FormStatus.INSERT_BLOCKED));
	}

	@Override
	public void onNew() {
		cleanFields();
		setFormStatus(FormStatus.INSERT_UNLOCKED);
		
		controller.searchCustomer();
		txtDescription.requestFocus();
	}

	@Override
	public void onSave() {
		String id = txtOsId.getText();
		String description = txtDescription.getText();
		BigDecimal value = new BigDecimal(txtValue.getText());
		OSStatus osStatus = OSStatus.values()[cbxOSStatus.getSelectedIndex()];
		OSRegisterDto osRegisterDto = new OSRegisterDto(customerResponseDto, description, value, osStatus);
		
		if (id.isBlank()) {
			controller.saveOS(osRegisterDto);
		} else {
			// update
		}
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
		
	}
	
	public void fillFields(OSResponseDto osResponseDto) {
		txtOsId.setText(Long.toString(osResponseDto.id()));
		txtCustomerName.setText(osResponseDto.customerResponseDto().name());
		txtDate.setText(osResponseDto.createdAt().toString());
		txtDescription.setText(osResponseDto.description());
		txtValue.setText(osResponseDto.value().toString());
	}

	private void setEnabledFields(boolean enabled) {
		txtOsId.setEnabled(enabled);
		txtCustomerName.setEnabled(enabled);
		btnSearchCustomer.setEnabled(enabled);
		txtDate.setEnabled(enabled);
		cbxOSStatus.setEnabled(enabled);
		txtDescription.setEnabled(enabled);
		txtValue.setEnabled(enabled);
	}
	
	public void setCustomer(CustomerResponseDto customerResponseDto) {
		this.customerResponseDto = customerResponseDto;
		txtCustomerName.setText(customerResponseDto.name());
	}

}
