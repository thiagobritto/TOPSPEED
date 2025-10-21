package com.tmb.ui.screens;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tmb.ui.components.AppButtonNavBar;

public abstract class AbstractFormRegister extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(AbstractFormRegister.class);
	protected JPanel contentPane;

	public AbstractFormRegister() {
		setFrameIcon(null);
		setClosable(true);
		setSize(788, 600);
		maximizedOnOpen();

		contentPane = new JPanel(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		contentPane.add(buttonPanel, BorderLayout.NORTH);

		JButton btnNew = new AppButtonNavBar("Novo");
		btnNew.setMnemonic(KeyEvent.VK_N);
		btnNew.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons/add_icon_28x28.png")));
		buttonPanel.add(btnNew);

		JButton btnSave = new AppButtonNavBar("Salvar");
		btnSave.setMnemonic(KeyEvent.VK_S);
		btnSave.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons/check_icon_28x28.png")));
		buttonPanel.add(btnSave);

		JButton btnEdit = new AppButtonNavBar("Alterar");
		btnEdit.setMnemonic(KeyEvent.VK_A);
		btnEdit.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons/edit_icon_28x28.png")));
		buttonPanel.add(btnEdit);

		JButton btnDelete = new AppButtonNavBar("Excluir");
		btnDelete.setMnemonic(KeyEvent.VK_E);
		btnDelete.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons/minus_icon_28x28.png")));
		buttonPanel.add(btnDelete);

		JButton btnSearch = new AppButtonNavBar("Localizar");
		btnSearch.setMnemonic(KeyEvent.VK_L);
		btnSearch.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons/search_icon_28x28.png")));
		buttonPanel.add(btnSearch);

		JButton btnCancel = new AppButtonNavBar("Cancelar");
		btnCancel.setMnemonic(KeyEvent.VK_C);
		btnCancel.setIcon(new ImageIcon(CustomerView.class.getResource("/images/icons/cancel_icon_28x28.png")));
		buttonPanel.add(btnCancel);

	}

	private void maximizedOnOpen() {
		try {
			setMaximum(true);
		} catch (Exception e) {
			logger.info("Erro ao maximizar tela de cadastro de clientes após inicialização.", e);
		}
	}
}
