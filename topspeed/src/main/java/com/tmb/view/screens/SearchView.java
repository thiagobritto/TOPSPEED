package com.tmb.view.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.function.Function;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.tmb.model.utils.functions.Search;

public class SearchView extends JDialog {

	private static final long serialVersionUID = 1L;
	private Object[] columnNames = { "Descrição" };

	private JPanel contentPane;
	private JTextField txtSearch;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton btnYes;
	private JButton btnCancel;
	private JLabel lblSearch;
	private JComboBox<String> cbxFilter;

	public SearchView(Window owner) {
		initComponents(owner);
	}

	private void initComponents(Window owner) {
		setSize(800, 600);
		setTitle("Buscar");
		setIconImage(null);
		setLocationRelativeTo(owner);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				table.clearSelection();
			}
		});

		KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		KeyStroke downKey = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);

		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		
		JPanel topPanel = new JPanel(new BorderLayout(5, 5));
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JPanel filterPanel = new JPanel(new BorderLayout(5, 5));
		topPanel.add(filterPanel, BorderLayout.WEST);
		
		JLabel lblFilter = new JLabel("Filtrar por");
		filterPanel.add(lblFilter, BorderLayout.NORTH);
		
		cbxFilter = new JComboBox<>(new String[] {"Descrição"});
		cbxFilter.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				Object item = e.getItem();
				lblSearch.setText(item.toString());
			}
		});
		filterPanel.add(cbxFilter, BorderLayout.CENTER);		
		
		JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
		topPanel.add(searchPanel, BorderLayout.CENTER);

		lblSearch = new JLabel(cbxFilter.getSelectedItem().toString());
		searchPanel.add(lblSearch, BorderLayout.NORTH);
		
		txtSearch = new JTextField();
		txtSearch.setPreferredSize(new Dimension(0, 25));
		searchPanel.add(txtSearch, BorderLayout.CENTER);
		
		InputMap imTxtSearch = txtSearch.getInputMap(JTextField.WHEN_FOCUSED);
		imTxtSearch.put(enterKey, "mudarFocus");
		imTxtSearch.put(downKey, "mudarFocus");

		Action mudarFocus = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getRowCount() > 0) {
					table.requestFocus();
					table.setRowSelectionInterval(0, 0);
				}
			}
		};

		txtSearch.getActionMap().put("mudarFocus", mudarFocus);

		tableModel = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnYes.setEnabled(table.getSelectedRow() >= 0);
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					dispose();
				}
			}
		});

		JScrollPane tableScrollPane = new JScrollPane(table);
		contentPane.add(tableScrollPane, BorderLayout.CENTER);

		InputMap imTable = table.getInputMap(JTable.WHEN_FOCUSED);
		imTable.put(enterKey, "selecionarLinha");

		Action selecionarLinhaAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};

		table.getActionMap().put("selecionarLinha", selecionarLinhaAction);

		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		contentPane.add(btnPanel, BorderLayout.SOUTH);

		btnYes = new JButton("Selecionar");
		btnYes.setEnabled(false);
		btnYes.addActionListener(e -> {
			dispose();
		});
		btnPanel.add(btnYes);

		btnPanel.add(Box.createHorizontalStrut(5));

		btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(e -> {
			table.clearSelection();
			dispose();
		});
		btnPanel.add(btnCancel);
		
		SwingUtilities.invokeLater(() -> txtSearch.requestFocus());
	}

	public void onSearch(Search search) {
		Timer searchTimer = new Timer(300, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				search.send(getFilterSelected(), txtSearch.getText());
			}
		});
		searchTimer.setRepeats(false); // Garante que o timer execute apenas uma vez

		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				searchTimer.restart();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				searchTimer.restart();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// Não usado para JTextFields simples
			}
		});
	}
	
	public void setFilters(String... filters) {
		cbxFilter.removeAllItems();
		for (String filter: filters) {
			cbxFilter.addItem(filter);
		}
		
		lblSearch.setText(getFilterSelected());
	}
	
	public String getFilterSelected() {
		return cbxFilter.getSelectedItem().toString();
	}
	
	public void setTableHeaders(Object... headers) {
		this.columnNames = headers;
		tableModel.setColumnIdentifiers(headers);
	}
	
	public <T> void updateTable(List<T> list, Function<T, Object[]> toArray) {
		tableModel.setRowCount(0);
		list.forEach(data -> tableModel.addRow(toArray.apply(data)));
	}

	public int getSelectedIndex() {
		return table.getSelectedRow();
	}

	public boolean isSelectedRow() {
		return table.getSelectedRow() >= 0;
	}

}
