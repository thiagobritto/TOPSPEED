package com.tmb;

import java.awt.EventQueue;

import com.formdev.flatlaf.FlatLightLaf;
import com.tmb.view.screens.MainView;

public class App {

	private static MainView mainView;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FlatLightLaf.setup();
				mainView = new MainView();
				mainView.setVisible(true);				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public static MainView getMainView() {
		return mainView;
	}
}
