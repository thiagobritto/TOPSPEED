package com.tmb.util;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PlaceholderTextField {

	public static void setupPlaceholder(JTextField textField, String placeholderText) {
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

	public static void setupPlaceholder(JPasswordField passwordField, String placeholderText) {
		passwordField.setText(placeholderText);
		passwordField.setForeground(Color.GRAY);
		passwordField.setEchoChar((char) 0); // Show placeholder text

		passwordField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (String.valueOf(passwordField.getPassword()).equals(placeholderText)) {
					passwordField.setText("");
					passwordField.setForeground(Color.BLACK);
					passwordField.setEchoChar('*'); // Hide password characters
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (String.valueOf(passwordField.getPassword()).isEmpty()) {
					passwordField.setText(placeholderText);
					passwordField.setForeground(Color.GRAY);
					passwordField.setEchoChar((char) 0); // Show placeholder text
				}
			}
		});
	}
}
