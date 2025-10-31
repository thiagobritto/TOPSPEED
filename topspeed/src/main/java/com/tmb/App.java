package com.tmb;

import java.awt.EventQueue;

import com.formdev.flatlaf.FlatLightLaf;
import com.tmb.view.screens.MainView;
import com.tmb.view.screens.ScreenFactory;

public class App {

	private static MainView mainView;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FlatLightLaf.setup();
				mainView = ScreenFactory.createMainView();
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
