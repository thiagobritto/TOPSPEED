package com.tmb.view.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tmb.view.components.ButtonNavBar;

public abstract class AbstractFormRegister extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(AbstractFormRegister.class);
	private JPanel contentPane;
	protected JPanel buttonPanel;
	private ButtonNavBar btnNew;
	private ButtonNavBar btnSave;
	private ButtonNavBar btnEdit;
	private ButtonNavBar btnDelete;
	private ButtonNavBar btnSearch;
	private ButtonNavBar btnCancel;
	
	protected enum StatusScreen {
		PREVIEW, INSERT, UPDATE
	}

	public AbstractFormRegister() {
		setFrameIcon(null);
		setClosable(true);
		setSize(788, 600);
		maximizedOnOpen();

		contentPane = new JPanel(new BorderLayout(0, 0));
		setContentPane(contentPane);

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		contentPane.add(buttonPanel, BorderLayout.NORTH);

		btnNew = new ButtonNavBar("Novo");
		btnNew.setMnemonic(KeyEvent.VK_N);
		btnNew.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons/add_icon_28x28.png")));
		btnNew.addActionListener(e -> onNew());
		buttonPanel.add(btnNew);

		btnSave = new ButtonNavBar("Salvar");
		btnSave.setMnemonic(KeyEvent.VK_S);
		btnSave.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons/check_icon_28x28.png")));
		btnSave.addActionListener(e -> onSave());
		buttonPanel.add(btnSave);

		btnEdit = new ButtonNavBar("Alterar");
		btnEdit.setMnemonic(KeyEvent.VK_A);
		btnEdit.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons/edit_icon_28x28.png")));
		btnEdit.addActionListener(e -> onEdit());
		buttonPanel.add(btnEdit);

		btnDelete = new ButtonNavBar("Excluir");
		btnDelete.setMnemonic(KeyEvent.VK_E);
		btnDelete.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons/minus_icon_28x28.png")));
		btnDelete.addActionListener(e -> onDelete());
		buttonPanel.add(btnDelete);

		btnSearch = new ButtonNavBar("Localizar");
		btnSearch.setMnemonic(KeyEvent.VK_L);
		btnSearch.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons/search_icon_28x28.png")));
		btnSearch.addActionListener(e -> onSearch());
		buttonPanel.add(btnSearch);

		btnCancel = new ButtonNavBar("Cancelar");
		btnCancel.setMnemonic(KeyEvent.VK_C);
		btnCancel.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons/cancel_icon_28x28.png")));
		btnCancel.addActionListener(e -> onCancel());
		buttonPanel.add(btnCancel);

		setStatusScreen(StatusScreen.PREVIEW);
	}
	
	public abstract void onNew();
	public abstract void onSave();
	public abstract void onEdit();
	public abstract void onDelete();
	public abstract void onSearch();
	public abstract void onCancel();
	
	public void setStatusScreen(StatusScreen status) {
		btnNew.setEnabled(false);
		btnSave.setEnabled(false);
		btnEdit.setEnabled(false);
		btnDelete.setEnabled(false);
		btnSearch.setEnabled(false);
		btnCancel.setEnabled(false);
		
		if (status.equals(StatusScreen.PREVIEW)) {
			btnNew.setEnabled(true);
			btnSearch.setEnabled(true);
		} else if (status.equals(StatusScreen.INSERT)) {
			btnSave.setEnabled(true);
			btnCancel.setEnabled(true);
		} else if (status.equals(StatusScreen.UPDATE)) {
			btnNew.setEnabled(true);
			btnEdit.setEnabled(true);
			btnDelete.setEnabled(true);
			btnSearch.setEnabled(true);
		}
	}

	private void maximizedOnOpen() {
		try {
			setMaximum(true);
		} catch (Exception e) {
			logger.info("Erro ao maximizar tela de cadastro de clientes após inicialização.", e);
		}
	}
}
