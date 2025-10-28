package com.tmb.controller;

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import com.tmb.dto.CustomerResponseDto;
import com.tmb.dto.OSRegisterDto;
import com.tmb.dto.OSResponseDto;
import com.tmb.model.exceptions.BusinessException;
import com.tmb.model.services.OSService;
import com.tmb.view.screens.FormStatus;
import com.tmb.view.screens.OSFormView;
import com.tmb.view.screens.ScreenFactory;
import com.tmb.view.screens.SearchView;

public class OSFormController {

	private final OSFormView view;
	private final OSService osService;
	private List<CustomerResponseDto> customerList;
	private List<OSResponseDto> osList;

	public OSFormController(OSFormView view, OSService osService) {
		this.view = view;
		this.osService = osService;
	}

	public void saveOS(OSRegisterDto osRegisterDto) {
		try {
			osService.save(osRegisterDto).ifPresent(osResponseDto -> {
				view.fillFields(osResponseDto);
				view.setFormStatus(FormStatus.UPDATE_BLOCKED);
				
				JOptionPane.showMessageDialog(view, "OS salva com sucesso!");
			});			
		} catch (IllegalArgumentException | BusinessException e) {
			JOptionPane.showMessageDialog(view, e.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Erro inesperado: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void searchCustomer() {
		SearchView searchView = ScreenFactory.createSearchView();
		searchView.setTitle("Selecionar cliente");
		searchView.setFilters("Nome");
		searchView.setTableHeaders("ID", "NOME", "TELEFONE");
		searchView.onSearch((filter, text) -> {
			customerList = osService.getCustomerByName(text);
			searchView.updateTable(customerList, c -> new Object[] { c.id(), c.name(), c.phone() });
		});
		searchView.setVisible(true);

		if (searchView.isSelectedRow()) {
			CustomerResponseDto customerResponseDto = customerList.get(searchView.getSelectedIndex());	
			view.setCustomer(customerResponseDto);
		}
	}
	
	public void searchOS() {
		SearchView searchView = ScreenFactory.createSearchView();
		searchView.setTitle("Selecionar OS");
		
		String NUMBER = "Numero";
		String CUSTOMER = "Cliente";		
		searchView.setFilters(NUMBER, CUSTOMER);
		
		searchView.setTableHeaders("ID", "CLIENTE", "VALOR", "STATUS");
		searchView.onSearch((filter, text) -> {
			if (filter.equals(NUMBER) && !text.isBlank()) {
				try {
					long id = Long.parseLong(text);
					osService.getOSByNumber(id).ifPresent(osResponseDto -> {
						searchView.updateTable(Arrays.asList(osResponseDto), o -> new Object[] { 
								o.id(), o.customerResponseDto().name(), o.value(), o.status().getName() });
					});
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(view, "O numero: " + text + ", não e valido.", "Atenção", JOptionPane.WARNING_MESSAGE);
				}
			} else if (filter.equals(CUSTOMER) && !text.isBlank()) {
				osList = osService.getOSByCustomerName(text);
				searchView.updateTable(osList, o -> new Object[] { 
						o.id(), o.customerResponseDto().name(), o.value(), o.status().getName() });				
			}
		});
		searchView.setVisible(true);

		
		  if (searchView.isSelectedRow()) {
			  OSResponseDto osResponseDto = osList.get(searchView.getSelectedIndex()); 
			  view.fillFields(osResponseDto);
			  view.setFormStatus(FormStatus.UPDATE_BLOCKED);
		  }
		 
	}

}
