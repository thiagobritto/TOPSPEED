package com.tmb.view.screens;

import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.tmb.controller.OSFormController;
import com.tmb.dto.CustomerResponseDto;
import com.tmb.dto.OSRegisterDto;
import com.tmb.dto.OSResponseDto;
import com.tmb.dto.OSUpdateDto;
import com.tmb.model.entities.OSStatus;
import com.tmb.utils.DateTimeUtils;
import com.tmb.view.components.PriceField;

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

		JPanel form = new JPanel(null);
		formPanel.add(form, BorderLayout.CENTER);
		
		JLabel lblOs = new JLabel("OS");
		lblOs.setBounds(10, 10, 60, 15);
		form.add(lblOs);
		
		txtOsId = new JTextField();
		txtOsId.setEditable(false);
		txtOsId.setBounds(10, 30, 60, 25);
		form.add(txtOsId);
		txtOsId.setColumns(10);
		
		JLabel lblCustomer = new JLabel("Cliente");
		lblCustomer.setBounds(80, 10, 60, 15);
		form.add(lblCustomer);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setEditable(false);
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(80, 30, 200, 25);
		form.add(txtCustomerName);
		
		btnSearchCustomer = new JButton("...");
		btnSearchCustomer.setToolTipText("Localizar cliente");
		btnSearchCustomer.setBounds(290, 30, 25, 25);
		btnSearchCustomer.addActionListener(e -> controller.searchCustomer());
		form.add(btnSearchCustomer);
		
		JLabel lblDate = new JLabel("Data");
		lblDate.setBounds(325, 10, 60, 15);
		form.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setColumns(10);
		txtDate.setBounds(325, 30, 120, 25);
		form.add(txtDate);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(455, 10, 60, 15);
		form.add(lblStatus);
		
		cbxOSStatus = new JComboBox<>(OSStatus.values());
		cbxOSStatus.setBounds(455, 30, 179, 25);
		form.add(cbxOSStatus);
		
		JLabel lblDescription = new JLabel("Descrição");
		lblDescription.setBounds(10, 60, 85, 15);
		form.add(lblDescription);
		
		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(10, 80, 305, 25);
		form.add(txtDescription);
		
		JLabel lblValue = new JLabel("Valor R$");
		lblValue.setBounds(325, 60, 60, 15);
		form.add(lblValue);
		
		txtValue = new PriceField();
		txtValue.setColumns(10);
		txtValue.setBounds(325, 80, 120, 25);
		form.add(txtValue);
		
		SwingUtilities.invokeLater(() -> setFormStatus(FormStatus.INSERT_BLOCKED));
	}

	@Override
	public void onNew() {
		if (controller.searchCustomer()) {
			setFormStatus(FormStatus.INSERT_UNLOCKED);
			cleanFields();
			
			txtDescription.requestFocus();			
		}
		
	}

	@Override
	public void onSave() {
		String id = txtOsId.getText();
		String description = txtDescription.getText();
		BigDecimal value = new BigDecimal(txtValue.getText());
		OSStatus osStatus = OSStatus.values()[cbxOSStatus.getSelectedIndex()];
		
		if (id.isBlank()) {
			OSRegisterDto osRegisterDto = new OSRegisterDto(customerResponseDto, null, description, null, value, osStatus);
			controller.saveOS(osRegisterDto);
		} else {
			OSUpdateDto osUpdateDto = new OSUpdateDto(Long.parseLong(id), customerResponseDto, null, description, null, value, osStatus);
			controller.updateOS(osUpdateDto);
		}
		
		requestFocus();
	}

	@Override
	public void onEdit() {
		setFormStatus(FormStatus.UPDATE_UNLOCKED);
	}

	@Override
	public void onDelete() {
		String idOs = txtOsId.getText();
		int option = JOptionPane.showConfirmDialog(this, "Deseja excluir a SO: " + idOs,
				"Confirmação", JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			controller.deleteOS(Long.parseLong(idOs));
			requestFocus();
		}
	}

	@Override
	public void onSearch() {
		controller.searchOS();
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
		txtOsId.setText("");
		txtCustomerName.setText("");
		txtDate.setText("");
		txtDescription.setText("");
		txtValue.setText("");
		cbxOSStatus.setSelectedIndex(0);
	}
	
	public void fillFields(OSResponseDto osResponseDto) {
		txtOsId.setText(Long.toString(osResponseDto.id()));
		txtCustomerName.setText(osResponseDto.customerResponseDto().name());
		txtDate.setText(DateTimeUtils.formatForDisplay(osResponseDto.createdAt()));
		txtDescription.setText(osResponseDto.description());
		txtValue.setText(osResponseDto.value().toString());
		cbxOSStatus.setSelectedIndex(osResponseDto.status().ordinal());
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
