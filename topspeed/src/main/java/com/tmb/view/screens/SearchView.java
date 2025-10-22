package com.tmb.view.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class SearchView extends JDialog {

	private static final long serialVersionUID = 1L;
	private Object[] columnNames = { "Descrição" };

	private JPanel contentPane;
	private JTextField txtSearch;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton btnYes;
	private JButton btnCancel;

	public SearchView(Window owner) {
		initComponents(owner);
	}

	private void initComponents(Window owner) {
		setSize(600, 400);
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

		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN && table.getRowCount() > 0) {
					table.requestFocus();
					table.setRowSelectionInterval(0, 0);
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER && table.getRowCount() > 0) {
					table.requestFocus();
					table.setRowSelectionInterval(0, 0);
				}
			}
		});
		setPlaceholder(txtSearch, "Buscar...");
		contentPane.add(txtSearch, BorderLayout.NORTH);

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
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE && table.getSelectedRow() >= 0) {
					dispose();
				}
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
	}
	
	private void setPlaceholder(JTextField textField, String placeholderText) {
		textField.setText(placeholderText);
		textField.setForeground(Color.GRAY);

		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals(placeholderText)) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().isEmpty()) {
					textField.setText(placeholderText);
					textField.setForeground(Color.GRAY);
				}
			}
		});
	}

	public void onSearch(Consumer<String> text) {
		Timer searchTimer = new Timer(300, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.accept(txtSearch.getText());
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

	public boolean isSelectedData() {
		return table.getSelectedRow() >= 0;
	}

}
