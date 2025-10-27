package com.tmb.model.utils.functions;

@FunctionalInterface
public interface Search {
	void send(String filter, String text);
}
