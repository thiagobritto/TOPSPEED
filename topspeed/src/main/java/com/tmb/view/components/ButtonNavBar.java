package com.tmb.view.components;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ButtonNavBar extends JButton {

	private static final long serialVersionUID = 1L;

	public ButtonNavBar(String text) {
		super(text);
		setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
	}
	
}
