package com.tmb;

import java.awt.EventQueue;

import com.formdev.flatlaf.FlatLightLaf;
import com.tmb.view.screens.MainView;

public class App {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FlatLightLaf.setup();
				//FlatDarkLaf.setup();
				MainView mainView = new MainView();
				mainView.setVisible(true);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
}
