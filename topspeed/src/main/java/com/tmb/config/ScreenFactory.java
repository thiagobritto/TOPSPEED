package com.tmb.config;

import java.awt.Window;

import com.tmb.controller.LoginController;
import com.tmb.ui.screens.LoginView;
import com.tmb.ui.screens.SearchView;

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
