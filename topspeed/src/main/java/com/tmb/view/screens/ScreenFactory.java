package com.tmb.view.screens;

import java.awt.Window;

import com.tmb.controller.CustomerController;
import com.tmb.controller.LoginController;
import com.tmb.model.dao.CustomerDao;
import com.tmb.model.dao.DatabaseFactory;
import com.tmb.model.services.CustomerService;
import com.tmb.model.utils.CustomerValidator;

public class ScreenFactory {

	public static LoginView createPdv() {
		return new LoginView(LoginController::new);
	}
	
	public static CustomerView createCustomerView() {
		CustomerDao customerDao = new CustomerDao(DatabaseFactory.createDatabase());
		CustomerValidator customerValidator = new CustomerValidator();
		CustomerService customerService = new CustomerService(customerDao, customerValidator);
		return new CustomerView(view -> new CustomerController(view, customerService));
	}

	public static SearchView createSearchView() {
		return new SearchView(null);
	}

	public static SearchView createSearchView(Window owner) {
		return new SearchView(owner);
	}

}
