package com.tmb.controller;

import java.util.List;

import com.tmb.domain.model.Customer;
import com.tmb.ui.screens.LoginView;

public class LoginController {

	private final LoginView view;
	private List<Customer> clList;

	public LoginController(LoginView view) {
		this.view = view;
	}

}
