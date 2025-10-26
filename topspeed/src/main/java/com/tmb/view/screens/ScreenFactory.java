package com.tmb.view.screens;

import java.awt.Window;

import com.tmb.controller.CustomerFormController;
import com.tmb.controller.LoginController;
import com.tmb.controller.OSFormController;
import com.tmb.model.dao.CustomerDao;
import com.tmb.model.dao.DatabaseFactory;
import com.tmb.model.services.CustomerService;
import com.tmb.model.services.OSService;
import com.tmb.model.utils.CustomerValidator;

public class ScreenFactory {
	
	private ScreenFactory() {
		// static
	}
	
	public static CustomerFormView createCustomerView() {
		CustomerDao customerDao = new CustomerDao(DatabaseFactory.createDatabase());
		CustomerValidator customerValidator = new CustomerValidator();
		CustomerService customerService = new CustomerService(customerDao, customerValidator);
		return new CustomerFormView(view -> new CustomerFormController(view, customerService));
	}
	
	public static OSFormView createOSFormView() {
		CustomerDao customerDao = new CustomerDao(DatabaseFactory.createDatabase());
		OSService osService = new OSService(customerDao);
		return new OSFormView(view -> new OSFormController(view, osService));
	}
	
	public static LoginView createLoginView() {
		return new LoginView(LoginController::new);
	}

	public static SearchView createSearchView() {
		return new SearchView(null);
	}

}
