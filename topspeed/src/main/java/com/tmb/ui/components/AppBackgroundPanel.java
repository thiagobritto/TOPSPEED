package com.tmb.ui.components;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AppBackgroundPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Image backgroundImage;

	public AppBackgroundPanel(String path) {
		backgroundImage = new ImageIcon(getClass().getResource(path)).getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
	
}
