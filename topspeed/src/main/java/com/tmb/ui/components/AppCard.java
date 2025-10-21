package com.tmb.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.tmb.util.FontStyle;

public class AppCard extends AppBackgroundPanel {

	private static final long serialVersionUID = 1L;
	private JLabel topText;
	private JLabel leftText;
	private JLabel centerText;
	private JLabel rightText;
	private JLabel bottonText;

	public AppCard() {
		this("");
	}
	
	public AppCard(Color bgColor) {
		this("");
		setBackground(bgColor);
	}
	
	public AppCard(String bgImagePath) {
		super(bgImagePath);
		setLayout(new BorderLayout(5, 5));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		topText = new JLabel();
		topText.setFont(new FontStyle(FontStyle.BOLD, FontStyle.SIZE_H2));
		add(topText, BorderLayout.NORTH);
		
		leftText = new JLabel();
		leftText.setFont(new FontStyle(FontStyle.BOLD, FontStyle.SIZE_H3));
		add(leftText, BorderLayout.WEST);
		
		centerText = new JLabel();
		centerText.setFont(new FontStyle(FontStyle.BOLD, FontStyle.SIZE_H3));
		add(centerText, BorderLayout.CENTER);
		
		rightText = new JLabel();
		rightText.setFont(new FontStyle(FontStyle.ITALIC, FontStyle.SIZE_H3));
		add(rightText, BorderLayout.EAST);
		
		bottonText = new JLabel();
		bottonText.setFont(new FontStyle(FontStyle.PLAIN, FontStyle.SIZE_P));
		add(bottonText, BorderLayout.SOUTH);
	}
	
	public void setTextColor(Color color) {
		topText.setForeground(color);
		leftText.setForeground(color);
		centerText.setForeground(color);
		rightText.setForeground(color);
		bottonText.setForeground(color);
	}

	public void setTopText(String text) {
		topText.setText(text);
	}
	
	public JLabel getTopText() {
		return topText;
	}
	
	public void setLeftText(String text) {
		leftText.setText(text);
	}
	
	public JLabel getLeftText() {
		return leftText;
	}
	
	public void setCenterText(String text) {
		centerText.setText(text);
	}
	
	public JLabel getCenterText() {
		return centerText;
	}
	
	public void setRightText(String text) {
		int maxLength = 10;
		String toolTipText = text;
		if (text != null && text.length() > maxLength) {
			text = text.substring(0, maxLength - 3) + "...";
        }
		rightText.setToolTipText(toolTipText);
		rightText.setText(text);
	}
	
	public JLabel getRightText() {
		return rightText;
	}
	
	public void setBottonText(String text) {
		bottonText.setText(text);
	}
	
	public JLabel getBottonText() {
		return bottonText;
	}

}
