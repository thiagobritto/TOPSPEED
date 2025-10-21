package com.tmb.ui.components;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JTextField;

import com.tmb.util.PlaceholderTextField;

public class AppTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	public AppTextField() {
		super();
		initComponent();
	}

	public AppTextField(int columns) {
		super(columns);
		initComponent();
	}
	
	private void initComponent() {
		setPreferredSize(new Dimension(0, 25));
	}
	
	public void setPlaceholder(String placeholderText) {
		PlaceholderTextField.setupPlaceholder(this, placeholderText);
	}
	
}
