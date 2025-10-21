package com.tmb.ui.components;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JPasswordField;

import com.tmb.util.PlaceholderTextField;

public class AppPasswordField extends JPasswordField {

	private static final long serialVersionUID = 1L;

	public AppPasswordField() {
		super();
		initComponent();
	}
	
	private void initComponent() {
		setPreferredSize(new Dimension(0, 25));
	}
	
	public void setPlaceholder(String placeholderText) {
		PlaceholderTextField.setupPlaceholder(this, placeholderText);
	}
	
}
