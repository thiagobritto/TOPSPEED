package com.tmb.view.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tmb.App;
import com.tmb.view.components.ButtonNavBar;
import com.tmb.view.styles.FontStyle;

public abstract class AbstractFormView extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(AbstractFormView.class);
	private JPanel contentPane;
	protected JPanel buttonPanel;
	protected JPanel formPanel;
	private ButtonNavBar btnNew;
	private ButtonNavBar btnSave;
	private ButtonNavBar btnEdit;
	private ButtonNavBar btnDelete;
	private ButtonNavBar btnSearch;
	private ButtonNavBar btnCancel;
	private FormStatus formStatus;
	private JLabel lblTitle;

	public AbstractFormView() {
		setSize(788, 600);
		setFrameIcon(null);
		setClosable(true);
		((BasicInternalFrameUI)getUI()).setNorthPane(null);
		maximizedOnOpen();

		contentPane = new JPanel(new BorderLayout(0, 0));
		setContentPane(contentPane);

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		contentPane.add(buttonPanel, BorderLayout.NORTH);

		btnNew = new ButtonNavBar("Novo");
		btnNew.setMnemonic(KeyEvent.VK_N);
		btnNew.setIcon(new ImageIcon(CustomerFormView.class.getResource("/images/icons/add_icon_28x28.png")));
		btnNew.addActionListener(e -> onNew());
		buttonPanel.add(btnNew);

		btnSave = new ButtonNavBar("Salvar");
		btnSave.setMnemonic(KeyEvent.VK_S);
		btnSave.setIcon(new ImageIcon(CustomerFormView.class.getResource("/images/icons/check_icon_28x28.png")));
		btnSave.addActionListener(e -> onSave());
		buttonPanel.add(btnSave);

		btnEdit = new ButtonNavBar("Alterar");
		btnEdit.setMnemonic(KeyEvent.VK_A);
		btnEdit.setIcon(new ImageIcon(CustomerFormView.class.getResource("/images/icons/edit_icon_28x28.png")));
		btnEdit.addActionListener(e -> onEdit());
		buttonPanel.add(btnEdit);

		btnDelete = new ButtonNavBar("Excluir");
		btnDelete.setMnemonic(KeyEvent.VK_E);
		btnDelete.setIcon(new ImageIcon(CustomerFormView.class.getResource("/images/icons/minus_icon_28x28.png")));
		btnDelete.addActionListener(e -> onDelete());
		buttonPanel.add(btnDelete);

		btnSearch = new ButtonNavBar("Localizar");
		btnSearch.setMnemonic(KeyEvent.VK_L);
		btnSearch.setIcon(new ImageIcon(CustomerFormView.class.getResource("/images/icons/search_icon_28x28.png")));
		btnSearch.addActionListener(e -> onSearch());
		buttonPanel.add(btnSearch);

		btnCancel = new ButtonNavBar("Cancelar");
		btnCancel.setMnemonic(KeyEvent.VK_C);
		btnCancel.setIcon(new ImageIcon(CustomerFormView.class.getResource("/images/icons/cancel_icon_28x28.png")));
		btnCancel.addActionListener(e -> onCancel());
		buttonPanel.add(btnCancel);
		
		formPanel = new JPanel(new BorderLayout(0, 0));
		getContentPane().add(formPanel, BorderLayout.CENTER);
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		titlePanel.setBackground(new Color(255, 128, 0));
		formPanel.add(titlePanel, BorderLayout.NORTH);
		
		lblTitle = new JLabel(getTitle());
		lblTitle.setFont(new FontStyle(FontStyle.BOLD, FontStyle.SIZE_H2));
		lblTitle.setForeground(Color.WHITE);
		titlePanel.add(lblTitle);
	}
	
	public abstract void onNew();
	public abstract void onSave();
	public abstract void onEdit();
	public abstract void onDelete();
	public abstract void onSearch();
	public abstract void onCancel();
	
	@Override
	public void setTitle(String title) {
		super.setTitle(title);
		lblTitle.setText(title);
		App.getMainView().setTitle(title);
	}
	
	public void setFormStatus(FormStatus status) {
		formStatus = status;
		
		btnNew.setEnabled(false);
		btnSave.setEnabled(false);
		btnEdit.setEnabled(false);
		btnDelete.setEnabled(false);
		btnSearch.setEnabled(false);
		btnCancel.setEnabled(false);
		
		if (status.equals(FormStatus.INSERT_BLOCKED)) {
			btnNew.setEnabled(true);
			btnSearch.setEnabled(true);
		} else if (status.equals(FormStatus.INSERT_UNLOCKED)) {
			btnSave.setEnabled(true);
			btnCancel.setEnabled(true);
		} else if (status.equals(FormStatus.UPDATE_BLOCKED)) {
			btnEdit.setEnabled(true);
			btnDelete.setEnabled(true);
			btnSearch.setEnabled(true);
			btnCancel.setEnabled(true);
		} else if (status.equals(FormStatus.UPDATE_UNLOCKED)) {
			btnSave.setEnabled(true);
		}
	}
	
	public FormStatus getFormStatus() {
		return formStatus;
	}

	private void maximizedOnOpen() {
		try {
			setMaximum(true);
		} catch (Exception e) {
			logger.info("Erro ao maximizar tela de cadastro de clientes após inicialização.", e);
		}
	}
}
