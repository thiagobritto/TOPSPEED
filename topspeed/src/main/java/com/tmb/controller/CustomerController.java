package com.tmb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.tmb.model.dto.CustomerDataSearchDto;
import com.tmb.model.dto.CustomerRegisterDto;
import com.tmb.model.services.CustomerService;
import com.tmb.model.utils.BusinessException;
import com.tmb.view.screens.CustomerView;
import com.tmb.view.screens.ScreenFactory;
import com.tmb.view.screens.SearchView;

public class CustomerController {

	private List<CustomerDataSearchDto> customerList = new ArrayList<>();
	private final CustomerView view;	
	private final CustomerService customerService;		

	public CustomerController(CustomerView view, CustomerService customerService) {
		this.view = view;
		this.customerService = customerService;
	}
	
	public void saveCustomer(CustomerRegisterDto customerRegisterDto) {
		try {
		    customerService.save(customerRegisterDto);
		    JOptionPane.showMessageDialog(view, "Cliente salvo com sucesso!");
		} catch (IllegalArgumentException | BusinessException e) {
		    JOptionPane.showMessageDialog(view, e.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(view, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void searchCustomer() {
		SearchView searchView = ScreenFactory.createSearchView();
		searchView.setTitle("Localizar Cliente");
		searchView.setSearchTitle("Nome");
		searchView.setTableHeaders("ID", "NOME", "TELEFONE");
		searchView.onSearch(text -> {
			customerList = customerService.getByName(text);
			searchView.updateTable(customerList, d -> new Object[] { d.id(), d.name(), d.phone() });
		});
		searchView.setVisible(true);
		
		if (searchView.isSelectedRow()) {
			CustomerDataSearchDto customerDataSearchDto = customerList.get(searchView.getSelectedIndex());
			view.fillFields(customerDataSearchDto);
		}
	}

	public List<CustomerDataSearchDto> getCustomersByName(String name) {
		return customerService.getByName(name);
	}
		
}
