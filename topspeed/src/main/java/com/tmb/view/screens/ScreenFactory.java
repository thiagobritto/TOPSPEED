package com.tmb.view.screens;

import java.awt.Window;

import com.tmb.controller.CustomerController;
import com.tmb.controller.LoginController;
import com.tmb.model.dao.CustomerDao;
import com.tmb.model.dao.DatabaseConnection;
import com.tmb.model.dao.DatabaseFactory;
import com.tmb.model.services.CustomerService;

public class ScreenFactory {

	public static LoginView createPdv() {
		return new LoginView(LoginController::new);
	}
	
	public static CustomerView createCustomerView() {
		CustomerDao customerDao = new CustomerDao(DatabaseFactory.createDatabase());
		CustomerService customerService = new CustomerService(customerDao);
		CustomerController customerController = new CustomerController(customerService);
		return new CustomerView(customerController);
	}

	public static SearchView createSearchView() {
		return new SearchView(null);
	}

	public static SearchView createSearchView(Window owner) {
		return new SearchView(owner);
	}

}
