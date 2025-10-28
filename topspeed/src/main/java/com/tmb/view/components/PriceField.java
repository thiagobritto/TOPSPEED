package com.tmb.view.components;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

public class PriceField extends JTextField {

	private static final long serialVersionUID = 1L;
	private static final String REGEX_FILTER = "\\d{0,}(\\.|,)?\\d{0,}";

	public PriceField() {
		AbstractDocument document = (AbstractDocument) getDocument();
		document.setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
					throws BadLocationException {

				StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
				sb.insert(offset, text);

				if (sb.toString().matches(REGEX_FILTER)) {
					super.insertString(fb, offset, text, attr);
				}
			}

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr)
					throws BadLocationException {

				StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
				sb.insert(offset, text);

				if (sb.toString().matches(REGEX_FILTER)) {
					super.replace(fb, offset, length, text, attr);
				}
			}
		});
	}

	@Override
	public void setText(String t) {
		super.setText(t.replace(".", ","));
	}

	@Override
	public String getText() {
		return super.getText().replace(",", ".");
	}
}
