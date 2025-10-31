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
	private JTextField txtService;
	private JTextField txtItemRepair;

	public OSFormView(Function<OSFormView, OSFormController> controller) {
		this.controller = controller.apply(this);
		this.initComponents();
	}

	private void initComponents() {
		setTitle("Cadastro de OS");

		JPanel form = new JPanel(null);
		formPanel.add(form, BorderLayout.CENTER);
		
		JLabel lblOs = new JLabel("Nº");
		lblOs.setBounds(10, 10, 60, 15);
		form.add(lblOs);
		
		txtOsId = new JTextField();
		txtOsId.setEditable(false);
		txtOsId.setBounds(10, 30, 60, 25);
		form.add(txtOsId);
		
		JLabel lblCustomer = new JLabel("Cliente");
		lblCustomer.setBounds(82, 10, 60, 15);
		form.add(lblCustomer);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setEditable(false);
		txtCustomerName.setBounds(82, 30, 200, 25);
		form.add(txtCustomerName);
		
		btnSearchCustomer = new JButton("...");
		btnSearchCustomer.setToolTipText("Localizar cliente");
		btnSearchCustomer.setBounds(292, 30, 25, 25);
		btnSearchCustomer.addActionListener(e -> controller.searchCustomer());
		form.add(btnSearchCustomer);
		
		JLabel lblDate = new JLabel("Data");
		lblDate.setBounds(327, 10, 60, 15);
		form.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setBounds(327, 30, 120, 25);
		form.add(txtDate);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(457, 10, 60, 15);
		form.add(lblStatus);
		
		cbxOSStatus = new JComboBox<>(OSStatus.values());
		cbxOSStatus.setBounds(457, 30, 179, 25);
		form.add(cbxOSStatus);
		
		JLabel lblItemRepair = new JLabel("Equipamento *");
		lblItemRepair.setBounds(10, 60, 120, 15);
		form.add(lblItemRepair);
		
		txtItemRepair = new JTextField();
		txtItemRepair.setBounds(10, 80, 307, 25);
		form.add(txtItemRepair);
		
		JLabel lblDescription = new JLabel("Descrição");
		lblDescription.setBounds(327, 60, 120, 15);
		form.add(lblDescription);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(327, 80, 309, 25);
		form.add(txtDescription);
		
		JLabel lblService = new JLabel("Serviço");
		lblService.setBounds(10, 110, 85, 15);
		form.add(lblService);
		
		txtService = new JTextField();
		txtService.setBounds(10, 130, 307, 25);
		form.add(txtService);
		
		JLabel lblValue = new JLabel("Valor R$ *");
		lblValue.setBounds(327, 110, 60, 15);
		form.add(lblValue);
		
		txtValue = new PriceField();
		txtValue.setColumns(10);
		txtValue.setBounds(327, 130, 120, 25);
		form.add(txtValue);
		
		SwingUtilities.invokeLater(() -> setFormStatus(FormStatus.INSERT_BLOCKED));
	}

	@Override
	public void onNew() {
		if (controller.searchCustomer()) {
			setFormStatus(FormStatus.INSERT_UNLOCKED);
			cleanFields();
			
			txtItemRepair.requestFocus();			
		}
		
	}

	@Override
	public void onSave() {
		String id = txtOsId.getText();
		String item = txtItemRepair.getText();
		String description = txtDescription.getText();
		String service = txtService.getText();
		BigDecimal value = new BigDecimal(txtValue.getText());
		OSStatus osStatus = OSStatus.values()[cbxOSStatus.getSelectedIndex()];
		
		if (id.isBlank()) {
			OSRegisterDto osRegisterDto = new OSRegisterDto(customerResponseDto, item, description, service, value, osStatus);
			controller.saveOS(osRegisterDto);
		} else {
			OSUpdateDto osUpdateDto = new OSUpdateDto(Long.parseLong(id), customerResponseDto, item, description, service, value, osStatus);
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
		txtItemRepair.setText("");
		txtDescription.setText("");
		txtService.setText("");
		txtValue.setText("");
		cbxOSStatus.setSelectedIndex(0);
	}
	
	public void fillFields(OSResponseDto osResponseDto) {
		txtOsId.setText(Long.toString(osResponseDto.id()));
		txtCustomerName.setText(osResponseDto.customerResponseDto().name());
		txtDate.setText(DateTimeUtils.formatForDisplay(osResponseDto.createdAt()));
		txtItemRepair.setText(osResponseDto.item());
		txtDescription.setText(osResponseDto.description());
		txtService.setText(osResponseDto.service());
		txtValue.setText(osResponseDto.value().toString());
		cbxOSStatus.setSelectedIndex(osResponseDto.status().ordinal());
	}

	private void setEnabledFields(boolean enabled) {
		txtOsId.setEnabled(enabled);
		txtCustomerName.setEnabled(enabled);
		btnSearchCustomer.setEnabled(enabled);
		txtDate.setEnabled(enabled);
		cbxOSStatus.setEnabled(enabled);
		txtItemRepair.setEnabled(enabled);
		txtDescription.setEnabled(enabled);
		txtService.setEnabled(enabled);
		txtValue.setEnabled(enabled);
	}
	
	public void setCustomer(CustomerResponseDto customerResponseDto) {
		this.customerResponseDto = customerResponseDto;
		txtCustomerName.setText(customerResponseDto.name());
	}
}
