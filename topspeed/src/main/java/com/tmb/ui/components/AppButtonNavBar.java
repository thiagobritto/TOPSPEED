package com.tmb.ui.components;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class AppButtonNavBar extends JButton {

	private static final long serialVersionUID = 1L;

	public AppButtonNavBar(String text) {
		super(text);
		setMargin(new Insets(5, 10, 5, 10));
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
	}
	
}
