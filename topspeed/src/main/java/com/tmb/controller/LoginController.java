package com.tmb.controller;

import java.util.List;

import com.tmb.model.entities.Customer;
import com.tmb.view.screens.LoginView;

public class LoginController {

	private final LoginView view;
	private List<Customer> clList;

	public LoginController(LoginView view) {
		this.view = view;
	}

}
