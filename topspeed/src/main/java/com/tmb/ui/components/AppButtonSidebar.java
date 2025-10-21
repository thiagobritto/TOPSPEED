package com.tmb.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class AppButtonSidebar extends JButton {

	private static final long serialVersionUID = 1L;

	public AppButtonSidebar(String text) {
		super(text);
		setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o botão
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Expande horizontalmente
		setBackground(new Color(70, 70, 70)); // Cor de fundo
		setForeground(Color.WHITE); // Cor do texto
		setFont(new Font("Arial", Font.BOLD, 14));
		setFocusPainted(false); // Remove a borda de foco
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	}
	
}
