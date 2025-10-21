package com.tmb.util;

import java.awt.Font;

public class FontStyle extends Font {

	private static final long serialVersionUID = 1L;
	
	public static String FONT_NAME = "Segoe UI";
	public static int SIZE_H1 = 26;
	public static int SIZE_H2 = 19;
	public static int SIZE_H3 = 15;
	public static int SIZE_P = 10;
	
	public FontStyle(int style, int size) {
		super(FONT_NAME, style, size);
	}
	
}
