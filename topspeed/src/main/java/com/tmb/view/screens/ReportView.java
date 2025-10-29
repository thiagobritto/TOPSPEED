package com.tmb.view.screens;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

import com.tmb.App;

import net.sf.jasperreports.swing.JRViewer;

public class ReportView extends JDialog {

	private static final long serialVersionUID = 1L;

	public ReportView(JRViewer viewer) {
		super(App.getMainView(), "Relatórios", true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setIconImage(null);
		setLocationRelativeTo(App.getMainView());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		KeyStroke escapeKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().registerKeyboardAction(e -> dispose(), escapeKey, JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		getContentPane().add(viewer);
	}

	
	

}
