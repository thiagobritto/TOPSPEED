package com.tmb.view.components;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

public class SidebarMainPanel extends BackgroundPanel {

	private static final long serialVersionUID = 1L;

	public SidebarMainPanel() {
		super("/images/banners/banner_main_aside_500x1080.png");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(220, 0));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

}
