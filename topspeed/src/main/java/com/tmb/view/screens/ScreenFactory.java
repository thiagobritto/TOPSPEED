package com.tmb.view.screens;

import java.awt.Window;

import com.tmb.controller.LoginController;

public class ScreenFactory {

	public static LoginView createPdv() {
		return new LoginView(LoginController::new);
	}

	public static SearchView createSearch() {
		return new SearchView(null);
	}

	public static SearchView createSearch(Window owner) {
		return new SearchView(owner);
	}

}
