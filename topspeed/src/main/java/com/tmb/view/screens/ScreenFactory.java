package com.tmb.view.screens;

import com.tmb.controller.CustomerFormController;
import com.tmb.controller.LoginController;
import com.tmb.controller.OSFormController;
import com.tmb.model.dao.CustomerDao;
import com.tmb.model.dao.DatabaseConnection;
import com.tmb.model.dao.DatabaseFactory;
import com.tmb.model.dao.OSDao;
import com.tmb.model.services.CustomerService;
import com.tmb.model.services.OSService;
import com.tmb.model.utils.CustomerValidator;
import com.tmb.model.utils.OSValidator;

public class ScreenFactory {
	
	private ScreenFactory() {
		// static
	}
	
	public static CustomerFormView createCustomerView() {
		CustomerValidator customerValidator = new CustomerValidator();
		CustomerDao customerDao = new CustomerDao(DatabaseFactory.createDatabase());
		CustomerService customerService = new CustomerService(customerDao, customerValidator);
		
		return new CustomerFormView(view -> new CustomerFormController(view, customerService));
	}
	
	public static OSFormView createOSFormView() {
		DatabaseConnection database = DatabaseFactory.createDatabase();
		OSValidator osValidator = new OSValidator();
		CustomerDao customerDao = new CustomerDao(database);
		OSDao osDao = new OSDao(database);
		OSService osService = new OSService(osValidator, customerDao, osDao);
		
		return new OSFormView(view -> new OSFormController(view, osService));
	}
	
	public static LoginView createLoginView() {
		return new LoginView(LoginController::new);
	}

	public static SearchView createSearchView() {
		return new SearchView(null);
	}

}
