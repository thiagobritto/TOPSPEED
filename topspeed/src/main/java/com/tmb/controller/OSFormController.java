package com.tmb.controller;

import java.util.List;
import java.util.Optional;

import com.tmb.model.dto.CustomerDataSearchDto;
import com.tmb.model.services.OSService;
import com.tmb.view.screens.FormStatus;
import com.tmb.view.screens.OSFormView;
import com.tmb.view.screens.ScreenFactory;
import com.tmb.view.screens.SearchView;

public class OSFormController {

	private final OSFormView view;
	private final OSService osService;
	private List<CustomerDataSearchDto> customerList;
	private CustomerDataSearchDto customerData;

	public OSFormController(OSFormView view, OSService osService) {
		this.view = view;
		this.osService = osService;
	}


	public Optional<String> searchCustomer() {
		SearchView searchView = ScreenFactory.createSearchView();
		searchView.setTitle("Selecionar cliente");
		searchView.setFilters("Nome");
		searchView.setTableHeaders("ID", "NOME", "TELEFONE");
		searchView.onSearch(text -> {
			customerList = osService.getByName(text);
			searchView.updateTable(customerList, c -> new Object[] { c.id(), c.name(), c.phone() });
		});
		searchView.setVisible(true);

		if (searchView.isSelectedRow()) {
			customerData = customerList.get(searchView.getSelectedIndex());
			return Optional.of(customerData.name());
		}
		
		return Optional.empty();
	}
	
}
