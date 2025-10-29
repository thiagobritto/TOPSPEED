package com.tmb.view.screens;

import com.tmb.App;
import com.tmb.controller.CustomerFormController;
import com.tmb.controller.LoginController;
import com.tmb.controller.OSFormController;
import com.tmb.model.dao.CustomerDao;
import com.tmb.model.dao.DatabaseConnection;
import com.tmb.model.dao.DatabaseFactory;
import com.tmb.model.dao.OSDao;
import com.tmb.model.services.CustomerService;
import com.tmb.model.services.OSService;
import com.tmb.model.validators.CustomerValidator;
import com.tmb.model.validators.OSValidator;

public class ScreenFactory {
	
	private ScreenFactory() {
		// static
	}
	
	public static CustomerFormView createCustomerView() {
		DatabaseConnection database = DatabaseFactory.createDatabase();
		CustomerDao customerDao = new CustomerDao(database);
		CustomerValidator customerValidator = new CustomerValidator();
		CustomerService customerService = new CustomerService(customerDao, customerValidator);
		
		return new CustomerFormView(view -> new CustomerFormController(view, customerService));
	}
	
	public static OSFormView createOSFormView() {
		DatabaseConnection database = DatabaseFactory.createDatabase();
		OSDao osDao = new OSDao(database);
		CustomerDao customerDao = new CustomerDao(database);
		OSValidator osValidator = new OSValidator();
		OSService osService = new OSService(osDao, customerDao, osValidator);
		
		return new OSFormView(view -> new OSFormController(view, osService));
	}
	
	public static LoginView createLoginView() {
		return new LoginView(LoginController::new);
	}

	public static SearchView createSearchView() {
		return new SearchView(App.getMainView());
	}

}
