package com.tmb.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OS {

	private long id;
	private Customer customer;
	private String item;
	private String description;
	private String service;
	private BigDecimal value;
	private LocalDateTime createdAt;
	private OSStatus status;
	
}
