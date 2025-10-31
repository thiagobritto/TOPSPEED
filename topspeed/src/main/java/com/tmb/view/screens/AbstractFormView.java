package com.tmb.view.screens;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.tmb.App;
import com.tmb.view.components.ButtonNavBar;
import com.tmb.view.styles.FontStyle;

public abstract class AbstractFormView extends AbstractFormLayout {

	private static final long serialVersionUID = 1L;
	
	private ButtonNavBar btnNew;
	private ButtonNavBar btnSave;
	private ButtonNavBar btnEdit;
	private ButtonNavBar btnDelete;
	private ButtonNavBar btnSearch;
	private ButtonNavBar btnCancel;
	private JLabel lblTitle;
	private FormStatus formStatus;	

	public AbstractFormView() {
		
		btnNew = new ButtonNavBar("Novo");
		btnNew.setMnemonic(KeyEvent.VK_N);
		btnNew.setIcon(new ImageIcon(CustomerFormView.class.getResource("/images/icons/add_icon_28x28.png")));
		btnNew.addActionListener(e -> onNew());
		header.add(btnNew);
		
		btnSave = new ButtonNavBar("Salvar");
		btnSave.setMnemonic(KeyEvent.VK_S);
		btnSave.setIcon(new ImageIcon(CustomerFormView.class.getResource("/images/icons/check_icon_28x28.png")));
		btnSave.addActionListener(e -> onSave());
		header.add(btnSave);
		
		btnEdit = new ButtonNavBar("Alterar");
		btnEdit.setMnemonic(KeyEvent.VK_A);
		btnEdit.setIcon(new ImageIcon(CustomerFormView.class.getResource("/images/icons/edit_icon_28x28.png")));
		btnEdit.addActionListener(e -> onEdit());
		header.add(btnEdit);
		
		btnDelete = new ButtonNavBar("Excluir");
		btnDelete.setMnemonic(KeyEvent.VK_E);
		btnDelete.setIcon(new ImageIcon(CustomerFormView.class.getResource("/images/icons/minus_icon_28x28.png")));
		btnDelete.addActionListener(e -> onDelete());
		header.add(btnDelete);
		
		btnSearch = new ButtonNavBar("Localizar");
		btnSearch.setMnemonic(KeyEvent.VK_L);
		btnSearch.setIcon(new ImageIcon(CustomerFormView.class.getResource("/images/icons/search_icon_28x28.png")));
		btnSearch.addActionListener(e -> onSearch());
		header.add(btnSearch);
		
		btnCancel = new ButtonNavBar("Cancelar");
		btnCancel.setMnemonic(KeyEvent.VK_C);
		btnCancel.setIcon(new ImageIcon(CustomerFormView.class.getResource("/images/icons/cancel_icon_28x28.png")));
		btnCancel.addActionListener(e -> onCancel());
		header.add(btnCancel);
	}
	
	public abstract void onNew();
	public abstract void onSave();
	public abstract void onEdit();
	public abstract void onDelete();
	public abstract void onSearch();
	public abstract void onCancel();
	
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

}
