package com.tmb.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.tmb.model.dto.CustomerResponseDto;
import com.tmb.model.dto.OSRegisterDto;
import com.tmb.model.services.OSService;
import com.tmb.model.utils.BusinessException;
import com.tmb.view.screens.FormStatus;
import com.tmb.view.screens.OSFormView;
import com.tmb.view.screens.ScreenFactory;
import com.tmb.view.screens.SearchView;

public class OSFormController {

	private final OSFormView view;
	private final OSService osService;
	private List<CustomerResponseDto> customerList;

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
			customerList = osService.getByName(text);
			searchView.updateTable(customerList, c -> new Object[] { c.id(), c.name(), c.phone() });
		});
		searchView.setVisible(true);

		if (searchView.isSelectedRow()) {
			CustomerResponseDto customerResponseDto = customerList.get(searchView.getSelectedIndex());	
			view.setCustomer(customerResponseDto);
		}
	}

}
