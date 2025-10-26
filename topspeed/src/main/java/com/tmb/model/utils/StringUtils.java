package com.tmb.model.utils;

public class StringUtils {

	private StringUtils() {
		// static
	}

	public static String nullIfEmpty(String string) {
		return (string == null || string.trim().isEmpty()) ? null : string.trim();
	}

}
