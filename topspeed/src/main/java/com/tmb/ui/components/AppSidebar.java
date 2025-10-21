package com.tmb.ui.components;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

public class AppSidebar extends AppBackgroundPanel {

	private static final long serialVersionUID = 1L;

	public AppSidebar(String path) {
		super(path);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(220, 0));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

}
