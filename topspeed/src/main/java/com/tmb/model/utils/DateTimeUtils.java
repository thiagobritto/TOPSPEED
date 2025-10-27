package com.tmb.model.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

	private static final DateTimeFormatter SQLITE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private DateTimeUtils() {
	}

	public static LocalDateTime parseSQLiteDate(String value) {
		return (value != null) ? LocalDateTime.parse(value, SQLITE_FORMATTER) : null;
	}

}
