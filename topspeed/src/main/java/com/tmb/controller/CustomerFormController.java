package com.tmb.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.tmb.model.dto.CustomerDataSearchDto;
import com.tmb.model.dto.CustomerRegisterDto;
import com.tmb.model.dto.CustomerUpdateDto;
import com.tmb.model.services.CustomerService;
import com.tmb.model.utils.BusinessException;
import com.tmb.view.screens.CustomerFormView;
import com.tmb.view.screens.FormStatus;
import com.tmb.view.screens.ScreenFactory;
import com.tmb.view.screens.SearchView;

public class CustomerFormController {

	private final CustomerFormView view;
	private final CustomerService customerService;
	private List<CustomerDataSearchDto> customerList;

	public CustomerFormController(CustomerFormView view, CustomerService customerService) {
		this.view = view;
		this.customerService = customerService;
	}

	public void saveCustomer(CustomerRegisterDto customerRegisterDto) {
		try {
			customerService.save(customerRegisterDto);
			view.setFormStatus(FormStatus.UPDATE_BLOCKED);

			JOptionPane.showMessageDialog(view, "Cliente salvo com sucesso!");
		} catch (IllegalArgumentException | BusinessException e) {
			JOptionPane.showMessageDialog(view, e.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Erro inesperado: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void updateCustomer(CustomerUpdateDto customerUpdateDto) {
		try {
			customerService.update(customerUpdateDto);
			view.setFormStatus(FormStatus.UPDATE_BLOCKED);

			JOptionPane.showMessageDialog(view, "Cliente atualizado com sucesso!");
		} catch (IllegalArgumentException | BusinessException e) {
			JOptionPane.showMessageDialog(view, e.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Erro inesperado: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void searchCustomer() {
		SearchView searchView = ScreenFactory.createSearchView();
		searchView.setTitle("Localizar Cliente");
		searchView.setFilters("Nome");
		searchView.setTableHeaders("ID", "NOME", "TELEFONE");
		searchView.onSearch(text -> {
			customerList = customerService.getByName(text);
			searchView.updateTable(customerList, c -> new Object[] { c.id(), c.name(), c.phone() });
		});
		searchView.setVisible(true);

		if (searchView.isSelectedRow()) {
			CustomerDataSearchDto customerDataSearchDto = customerList.get(searchView.getSelectedIndex());
			view.fillFields(customerDataSearchDto);
			view.setFormStatus(FormStatus.UPDATE_BLOCKED);
		}
	}
	
	public void deleteCustomer(long id) {
		try {
			customerService.delete(id);
			view.setFormStatus(FormStatus.INSERT_BLOCKED);
			view.cleanFields();
			
			JOptionPane.showMessageDialog(view, "Cliente removido com sucesso!");
		} catch (IllegalArgumentException | BusinessException e) {
			JOptionPane.showMessageDialog(view, e.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Erro inesperado: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
