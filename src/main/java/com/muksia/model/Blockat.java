/**
 * Copyright Flexpay AB
 */
package com.muksia.model;

import org.springframework.data.annotation.Id;

public class Blockat {

	@Id
	private String id;

	public String row;

	public Blockat(final String row) {
		this.row = row;
	}

	public String getRow() {
		return row;
	}
}
