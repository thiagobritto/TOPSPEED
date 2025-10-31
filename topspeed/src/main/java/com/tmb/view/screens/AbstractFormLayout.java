package com.tmb.view.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyVetoException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.tmb.App;
import com.tmb.view.styles.FontStyle;

public abstract class AbstractFormLayout extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	protected JPanel body;
	protected JPanel header;
	protected JPanel section;
	protected JPanel sectionHeader;
	protected JPanel sectionMain;
	protected JLabel lblTitle;
	

	public AbstractFormLayout() {
		this.initComponents();
	}
	
	private void initComponents() {
		setSize(788, 600);
		setFrameIcon(null);
		((BasicInternalFrameUI)getUI()).setNorthPane(null);
		
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		body = new JPanel(new BorderLayout());
		setContentPane(body);
		
		header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		body.add(header, BorderLayout.NORTH);
		
		section = new JPanel(new BorderLayout());
		body.add(section, BorderLayout.CENTER);
		
		sectionHeader = new JPanel();
		sectionHeader.setLayout(new BoxLayout(sectionHeader, BoxLayout.X_AXIS));
		sectionHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		sectionHeader.setBackground(new Color(255, 128, 0));
		section.add(sectionHeader, BorderLayout.NORTH);
		
		lblTitle = new JLabel(getTitle());
		lblTitle.setFont(new FontStyle(FontStyle.BOLD, FontStyle.SIZE_H3));
		lblTitle.setForeground(Color.WHITE);
		sectionHeader.add(lblTitle);

		sectionMain = new JPanel(null);
		section.add(sectionMain, BorderLayout.CENTER);
		
	}
	
	@Override
	public void setTitle(String title) {
		super.setTitle(title);
		lblTitle.setText(title);
		App.getMainView().setTitle(title);
	}

}
